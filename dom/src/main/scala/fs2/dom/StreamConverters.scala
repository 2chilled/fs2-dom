/*
 * Copyright 2022 Arman Bilge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fs2
package dom

import cats.effect.kernel.Async
import cats.effect.kernel.Resource
import cats.syntax.all._
import org.scalajs.dom.ReadableStream

import scala.scalajs.js
import scala.scalajs.js.typedarray.Uint8Array

private[dom] object StreamConverters {

  def readReadableStream[F[_]](
      readableStream: F[ReadableStream[Uint8Array]],
      cancelAfterUse: Boolean
  )(implicit
      F: Async[F]
  ): Stream[F, Byte] = {
    def read(readableStream: ReadableStream[Uint8Array]) =
      Stream.bracket(F.delay(readableStream.getReader()))(r => F.delay(r.releaseLock())).flatMap {
        reader =>
          Stream.unfoldChunkEval(reader) { reader =>
            F.fromPromise(F.delay(reader.read())).map { chunk =>
              if (chunk.done)
                None
              else
                Some((fs2.Chunk.uint8Array(chunk.value), reader))
            }
          }
      }

    if (cancelAfterUse)
      Stream.bracketCase(readableStream)(cancelReadableStream(_, _)).flatMap(read(_))
    else
      Stream.eval(readableStream).flatMap(read(_))
  }

  private[this] def cancelReadableStream[F[_], A](
      rs: ReadableStream[A],
      exitCase: Resource.ExitCase
  )(implicit F: Async[F]): F[Unit] = F.fromPromise {
    F.delay {
      // Best guess: Firefox internally locks a ReadableStream after it is "drained"
      // This checks if the stream is locked before canceling it to avoid an error
      if (!rs.locked) exitCase match {
        case Resource.ExitCase.Succeeded =>
          rs.cancel(js.undefined)
        case Resource.ExitCase.Errored(ex) =>
          rs.cancel(ex.toString())
        case Resource.ExitCase.Canceled =>
          rs.cancel(js.undefined)
      }
      else js.Promise.resolve[Unit](())
    }
  }

}

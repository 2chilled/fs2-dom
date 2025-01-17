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

package fs2.dom

import cats.effect.kernel.Async
import cats.effect.kernel.Sync
import org.scalajs.dom.window

trait ClipboardDsl[F[_]] {

  def readText: F[String]

  def writeText(text: String): F[Unit]

}

object ClipboardDsl {

  implicit def interpreter[F[_] : Async]: ClipboardDsl[F] = new ClipboardDsl[F] {

    def readText = Async[F].fromPromise(Sync[F].delay(window.navigator.clipboard.readText()))

    def writeText(text: String) = Async[F].fromPromise(Sync[F].delay(window.navigator.clipboard.writeText(text)))

  }

  def apply[F[_]](implicit ev: ClipboardDsl[F]) = ev

}

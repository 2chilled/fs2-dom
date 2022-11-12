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

import cats.effect.IO
import cats.effect.kernel.Async
import cats.effect.kernel.Ref
import org.scalajs.dom

opaque type Dom[F[_]] = Async[F]
object Dom {
  implicit inline def forIO: Dom[IO] = IO.asyncForIO
  inline def forAsync[F[_]](using F: Async[F]): Dom[F] = F
}

opaque type Node[F[_]] = dom.Node
opaque type Element[F[_]] <: Node[F] = dom.Element
opaque type HtmlElement[F[_]] <: Element[F] = dom.HTMLElement
opaque type HtmlAnchorElement[F[_]] <: HtmlElement[F] = dom.HTMLAnchorElement
opaque type HtmlAreaElement[F[_]] <: HtmlElement[F] = dom.HTMLAreaElement
opaque type HtmlAudioElement[F[_]] <: HtmlElement[F] = dom.HTMLAudioElement
opaque type HtmlBaseElement[F[_]] <: HtmlElement[F] = dom.HTMLBaseElement
opaque type HtmlBodyElement[F[_]] <: HtmlElement[F] = dom.HTMLBodyElement

opaque type HtmlButtonElement[F[_]] <: HtmlElement[F] = dom.HTMLButtonElement
object HtmlButtonElement {
  extension [F[_]](input: HtmlButtonElement[F]) {
    private inline def self = input.asInstanceOf[dom.HTMLButtonElement]

    def value(using Dom[F]): Ref[F, String] =
      new WrappedRef[F, String] {
        def unsafeGet() = self.value
        def unsafeSet(s: String) = self.value = s
      }
  }
}

opaque type HtmlBrElement[F[_]] <: HtmlElement[F] = dom.HTMLBRElement
opaque type HtmlCanvasElement[F[_]] <: HtmlElement[F] = dom.HTMLCanvasElement
opaque type HtmlDataListElement[F[_]] <: HtmlElement[F] = dom.HTMLDataListElement
opaque type HtmlDivElement[F[_]] <: HtmlElement[F] = dom.HTMLDivElement
opaque type HtmlDListElement[F[_]] <: HtmlElement[F] = dom.HTMLDListElement
opaque type HtmlEmbedElement[F[_]] <: HtmlElement[F] = dom.HTMLEmbedElement
opaque type HtmlFieldSetElement[F[_]] <: HtmlElement[F] = dom.HTMLFieldSetElement
opaque type HtmlFormElement[F[_]] <: HtmlElement[F] = dom.HTMLFormElement
opaque type HtmlHeadElement[F[_]] <: HtmlElement[F] = dom.HTMLHeadElement
opaque type HtmlHeadingElement[F[_]] <: HtmlElement[F] = dom.HTMLHeadingElement
opaque type HtmlHrElement[F[_]] <: HtmlElement[F] = dom.HTMLHRElement
opaque type HtmlHtmlElement[F[_]] <: HtmlElement[F] = dom.HTMLHtmlElement
opaque type HtmlIFrameElement[F[_]] <: HtmlElement[F] = dom.HTMLIFrameElement
opaque type HtmlImageElement[F[_]] <: HtmlElement[F] = dom.HTMLImageElement

opaque type HtmlInputElement[F[_]] <: HtmlElement[F] = dom.HTMLInputElement
object HtmlInputElement {
  extension [F[_]](input: HtmlInputElement[F]) {
    private inline def self = input.asInstanceOf[dom.HTMLInputElement]

    def checked(using Dom[F]): Ref[F, Boolean] =
      new WrappedRef[F, Boolean] {
        def unsafeGet() = self.checked
        def unsafeSet(b: Boolean) = self.checked = b
      }

    def value(using Dom[F]): Ref[F, String] =
      new WrappedRef[F, String] {
        def unsafeGet() = self.value
        def unsafeSet(s: String) = self.value = s
      }
  }
}

opaque type HtmlLinkElement[F[_]] <: HtmlElement[F] = dom.HTMLLinkElement
opaque type HtmlLabelElement[F[_]] <: HtmlElement[F] = dom.HTMLLabelElement
opaque type HtmlLegendElement[F[_]] <: HtmlElement[F] = dom.HTMLLegendElement
opaque type HtmlLiElement[F[_]] <: HtmlElement[F] = dom.HTMLLIElement
opaque type HtmlMapElement[F[_]] <: HtmlElement[F] = dom.HTMLMapElement
opaque type HtmlMenuElement[F[_]] <: HtmlElement[F] = dom.HTMLMenuElement
opaque type HtmlMetaElement[F[_]] <: HtmlElement[F] = dom.HTMLMetaElement
opaque type HtmlModElement[F[_]] <: HtmlElement[F] = dom.HTMLModElement
opaque type HtmlObjectElement[F[_]] <: HtmlElement[F] = dom.HTMLObjectElement
opaque type HtmlOListElement[F[_]] <: HtmlElement[F] = dom.HTMLOListElement
opaque type HtmlOptGroupElement[F[_]] <: HtmlElement[F] = dom.HTMLOptGroupElement

opaque type HtmlOptionElement[F[_]] <: HtmlElement[F] = dom.HTMLOptionElement
object HtmlOptionElement {
  extension [F[_]](input: HtmlOptionElement[F]) {
    private inline def self = input.asInstanceOf[dom.HTMLOptionElement]

    def value(using Dom[F]): Ref[F, String] =
      new WrappedRef[F, String] {
        def unsafeGet() = self.value
        def unsafeSet(s: String) = self.value = s
      }
  }
}

opaque type HtmlParagraphElement[F[_]] <: HtmlElement[F] = dom.HTMLParagraphElement
opaque type HtmlParamElement[F[_]] <: HtmlElement[F] = dom.HTMLParamElement
opaque type HtmlPreElement[F[_]] <: HtmlElement[F] = dom.HTMLPreElement
opaque type HtmlProgressElement[F[_]] <: HtmlElement[F] = dom.HTMLProgressElement
opaque type HtmlQuoteElement[F[_]] <: HtmlElement[F] = dom.HTMLQuoteElement
opaque type HtmlScriptElement[F[_]] <: HtmlElement[F] = dom.HTMLScriptElement

opaque type HtmlSelectElement[F[_]] <: HtmlElement[F] = dom.HTMLSelectElement
object HtmlSelectElement {
  extension [F[_]](input: HtmlSelectElement[F]) {
    private inline def self = input.asInstanceOf[dom.HTMLSelectElement]

    def value(using Dom[F]): Ref[F, String] =
      new WrappedRef[F, String] {
        def unsafeGet() = self.value
        def unsafeSet(s: String) = self.value = s
      }
  }
}

opaque type HtmlSourceElement[F[_]] <: HtmlElement[F] = dom.HTMLSourceElement
opaque type HtmlSpanElement[F[_]] <: HtmlElement[F] = dom.HTMLSpanElement
opaque type HtmlStyleElement[F[_]] <: HtmlElement[F] = dom.HTMLStyleElement
opaque type HtmlTableElement[F[_]] <: HtmlElement[F] = dom.HTMLTableElement
opaque type HtmlTableCaptionElement[F[_]] <: HtmlElement[F] = dom.HTMLTableCaptionElement
opaque type HtmlTableCellElement[F[_]] <: HtmlElement[F] = dom.HTMLTableCellElement
opaque type HtmlTableColElement[F[_]] <: HtmlElement[F] = dom.HTMLTableColElement
opaque type HtmlTableRowElement[F[_]] <: HtmlElement[F] = dom.HTMLTableRowElement
opaque type HtmlTableSectionElement[F[_]] <: HtmlElement[F] = dom.HTMLTableSectionElement

opaque type HtmlTextAreaElement[F[_]] <: HtmlElement[F] = dom.HTMLTextAreaElement
object HtmlTextAreaElement {
  extension [F[_]](input: HtmlTextAreaElement[F]) {
    private inline def self = input.asInstanceOf[dom.HTMLTextAreaElement]

    def value(using Dom[F]): Ref[F, String] =
      new WrappedRef[F, String] {
        def unsafeGet() = self.value
        def unsafeSet(s: String) = self.value = s
      }
  }
}

opaque type HtmlTitleElement[F[_]] <: HtmlElement[F] = dom.HTMLTitleElement
opaque type HtmlTrackElement[F[_]] <: HtmlElement[F] = dom.HTMLTrackElement
opaque type HtmlUListElement[F[_]] <: HtmlElement[F] = dom.HTMLUListElement
opaque type HtmlVideoElement[F[_]] <: HtmlElement[F] = dom.HTMLVideoElement

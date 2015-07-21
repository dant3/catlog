package com.github.dant3.catlog.ui.terminal

import com.googlecode.lanterna.terminal.Terminal

import scala.sys.ShutdownHookThread

class PrivateModeEscapingTerminal(wrappedTerminal:Terminal) extends ProxyTerminal(wrappedTerminal) {
  private var privateModeMaintainer:Option[ShutdownHookThread] = None

  override def enterPrivateMode(): Unit = privateModeMaintainer match {
    case Some(maintainer) ⇒ // private mode already started - do nothing
    case None ⇒
      super.enterPrivateMode()
      privateModeMaintainer = Some(ShutdownHookThread {
        exitPrivateMode()
      })
  }
  override def exitPrivateMode(): Unit = privateModeMaintainer match {
    case Some(maintainer) ⇒
      super.exitPrivateMode()
      maintainer.remove()
      privateModeMaintainer = None
    case None ⇒ ???
  }
}

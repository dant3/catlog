package com.github.dant3.catlog.ui.terminal

import javax.swing.WindowConstants

import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame
import com.googlecode.lanterna.terminal.{DefaultTerminalFactory, Terminal, TerminalFactory}

class CatlogTerminalFactory extends TerminalFactory {
  private val defaultTerminalFactory = new DefaultTerminalFactory

  def setUseSwingTerminalFrame(useSwing:Boolean):TerminalFactory = {
    defaultTerminalFactory.setSuppressSwingTerminalFrame(!useSwing)
    this
  }

  override def createTerminal(): Terminal =
    tuneSwingWindow(defaultTerminalFactory.createTerminal())


  private def tuneSwingWindow(terminal: Terminal) = terminal match {
    case swingFrame: SwingTerminalFrame ⇒
      swingFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE)
      swingFrame
    case anythingElse ⇒ anythingElse
  }
}

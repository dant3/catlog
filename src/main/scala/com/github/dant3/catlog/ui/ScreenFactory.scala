package com.github.dant3.catlog.ui

import java.io.IOException

import com.github.dant3.catlog.ui.terminal.CatlogTerminalFactory
import com.googlecode.lanterna.screen.{Screen, TerminalScreen}
import com.googlecode.lanterna.terminal.Terminal

class ScreenFactory {
  private val terminalFactory = new CatlogTerminalFactory()

  def createScreen(): Screen = try {
    new TerminalScreen(createTerminal(useSwing = false))
  } catch {
    case ex:IOException if ex.getMessage.startsWith("Timeout while waiting for terminal size report!") ⇒
      new TerminalScreen(createTerminal(useSwing = true))
  }

  private def createTerminal(useSwing:Boolean):Terminal = {
    terminalFactory.setUseSwingTerminalFrame(useSwing).createTerminal()
  }
}

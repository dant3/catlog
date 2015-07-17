package com.github.dant3.catlog.ui

import java.io.IOException

import com.googlecode.lanterna.screen.{TerminalScreen, Screen}
import com.googlecode.lanterna.terminal.{Terminal, DefaultTerminalFactory}

class ScreenFactory {
  private val terminalFactory = new DefaultTerminalFactory()

  def createScreen(): Screen = try {
    new TerminalScreen(createTerminal(useSwing = false))
  } catch {
    case ex:IOException if ex.getMessage.startsWith("Timeout while waiting for terminal size report!") ⇒
      new TerminalScreen(createTerminal(useSwing = true))
  }

  private def createTerminal(useSwing:Boolean):Terminal = {
    terminalFactory.setSuppressSwingTerminalFrame(!useSwing).createTerminal()
  }
}

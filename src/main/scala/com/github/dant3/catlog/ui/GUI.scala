package com.github.dant3.catlog.ui

import com.github.dant3.catlog.util.GuiThreadExecutor
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.gui2._
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory

object GUI {
  lazy val terminalFactory = new DefaultTerminalFactory().setSuppressSwingTerminalFrame(true)
  lazy val terminal = terminalFactory.createTerminal()
  lazy val screen = new TerminalScreen(terminal)
  lazy val windowManager = new DefaultWindowManager()
  lazy val background = new EmptySpace(TextColor.ANSI.DEFAULT)
  lazy val gui = new MultiWindowTextGUI(screen, windowManager, background)

  lazy val executor = new GuiThreadExecutor(gui)

  def run(guiApp: WindowBasedTextGUI ⇒ Unit): Unit = {
    screen.startScreen()
    try {
      guiApp(gui)
    } finally {
      screen.stopScreen()
    }
  }
}

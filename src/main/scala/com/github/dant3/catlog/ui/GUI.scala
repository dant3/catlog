package com.github.dant3.catlog.ui

import com.github.dant3.catlog.util.GuiThreadExecutor
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.gui2._

object GUI {
  lazy val screenFactory = new ScreenFactory()
  lazy val screen = screenFactory.createScreen()
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

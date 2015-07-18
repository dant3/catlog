package com.github.dant3.catlog.ui

import java.awt.event.{WindowEvent, WindowAdapter}
import java.io.IOException
import javax.swing.JFrame

import com.googlecode.lanterna.screen.{Screen, TerminalScreen}
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame
import com.googlecode.lanterna.terminal.{DefaultTerminalFactory, Terminal}

class ScreenFactory {
  private val terminalFactory = new DefaultTerminalFactory()

  def createScreen(): Screen = try {
    new TerminalScreen(createTerminal(useSwing = false))
  } catch {
    case ex:IOException if ex.getMessage.startsWith("Timeout while waiting for terminal size report!") ⇒
      new TerminalScreen(createTerminal(useSwing = true))
  }

  private def createTerminal(useSwing:Boolean):Terminal = {
    tuneSwingWindow(terminalFactory.setSuppressSwingTerminalFrame(!useSwing).createTerminal())
  }

  private def tuneSwingWindow(terminal: Terminal) = terminal match {
    case swingFrame: SwingTerminalFrame ⇒
      swingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
      swingFrame.addWindowListener(new WindowAdapter {
        override def windowClosing(event:WindowEvent): Unit = {
          // HACK! ScreenFactory shouldn't be aware of GUI internals, screen and windows
          import scala.collection.JavaConversions._
          GUI.gui.getWindows.foreach(_.close())
          GUI.screen.stopScreen()
        }
      })
      swingFrame
    case anythingElse ⇒ anythingElse
  }
}

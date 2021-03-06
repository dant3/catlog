package com.github.dant3.catlog.util

import com.googlecode.lanterna.gui2.{TextGUIThread, TextGUI}

import scala.concurrent.ExecutionContextExecutor

class GuiThreadExecutor(guiThread:TextGUIThread) extends ExecutionContextExecutor {
  def this(gui:TextGUI) = this(gui.getGUIThread)

  override def reportFailure(cause: Throwable): Unit = ???
  override def execute(command: Runnable): Unit = if (Thread.currentThread() == guiThread.getThread) {
    command.run()
  } else {
    guiThread.invokeLater(command)
  }
}

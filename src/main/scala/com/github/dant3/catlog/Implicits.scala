package com.github.dant3.catlog

import java.util.concurrent.Executor

import com.github.dant3.catlog.util.GuiThreadExecutor
import com.googlecode.lanterna.gui2.TextGUIThread

import scala.language.implicitConversions

trait Implicits {
  implicit def toRunnable(block: ⇒ Any):Runnable = new Runnable {
    override def run(): Unit = block
  }

  implicit def guiThreadToExecutor(guiThread:TextGUIThread):Executor = new GuiThreadExecutor(guiThread)
}

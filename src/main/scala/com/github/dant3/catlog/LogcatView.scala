package com.github.dant3.catlog

import com.googlecode.lanterna.gui2.AbstractListBox

class LogcatView extends AbstractListBox[LogcatView] with Implicits {
  val reader:LogcatReader = new LogcatReader() {
    override protected def logString(logcatLine: String): Unit = {
      GUI.executor.execute(addItem(logcatLine))
    }
  }

  Runtime.getRuntime.addShutdownHook(new Thread(new Runnable {
    override def run(): Unit = reader.shutdown()
  }))
}

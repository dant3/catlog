package com.github.dant3.catlog

import com.googlecode.lanterna.gui2._

object Main extends HelloWorld with Implicits {
  def main(args:Array[String]) = {
    GUI.run { gui ⇒
      lazy val logcatView = new LogcatView
      gui.addWindowAndWait(new BasicWindow("Catlog") { window ⇒
        setComponent(new Panel() {
          addComponent(logcatView)
          addComponent(new Button("Quit", window.close()))
        })
      })
      logcatView.reader.shutdown()
    }
  }
}

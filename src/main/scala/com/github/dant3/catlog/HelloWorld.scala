package com.github.dant3.catlog

import com.googlecode.lanterna.gui2._
import com.googlecode.lanterna.gui2.dialogs.{MessageDialogButton, MessageDialog}

trait HelloWorld {
  def helloWorldWindow(gui:WindowBasedTextGUI) = new BasicWindow("Catlog") { window ⇒
    val nameInputBox = new TextBox()

    setComponent(new Panel() {
      addComponent(new Label("What is your name?"))
      addComponent(nameInputBox)
      addComponent(new Button("Greet", new Runnable {
        override def run(): Unit = {
          MessageDialog.showMessageDialog(gui, "Hello!", s"Hello, ${nameInputBox.getText}!", MessageDialogButton.OK)
        }
      }))
      addComponent(new Button("Quit", new Runnable {
        override def run(): Unit = {
          window.close()
        }
      }))
    })
  }
}

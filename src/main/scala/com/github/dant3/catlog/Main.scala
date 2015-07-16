package com.github.dant3.catlog

import com.github.dant3.catlog.adb.{Device, Adb}
import com.github.dant3.catlog.ui.{GUI, LogcatView, Toolbar}
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.gui2._
import com.googlecode.lanterna.gui2.dialogs.{ActionListDialogBuilder, MessageDialogBuilder, MessageDialogButton}
import com.googlecode.lanterna.input.KeyType

object Main extends HelloWorld with Implicits {
  val title = "Catlog"

  lazy val logcatTermSize = new TerminalSize(170, 38)
  private var logcatView:Option[LogcatView] = None

  def main(args:Array[String]) = {
    GUI.run { implicit gui ⇒

      gui.addWindowAndWait(new BasicWindow(title) { self ⇒
        implicit val window = self
        setComponent(createUi(Adb.devices.headOption))
      })
      logcatView.foreach(_.logcatProcess.destroy())
    }
  }

  private def createUi(device:Option[Device] = None)(implicit gui:WindowBasedTextGUI, window:Window):Panel = {
    logcatView = Some(logcatView match {
      case Some(view) ⇒
        view.logcatProcess.destroy()
        new LogcatView(logcatTermSize, device)
      case None ⇒ new LogcatView(logcatTermSize, device)
    })

    Panels.vertical(
      createToolbar(device),
      logcatView.get
    )
  }


  private def createToolbar(device:Option[Device])(implicit gui:WindowBasedTextGUI, window:Window):Toolbar = {
    new Toolbar(gui).
        addAction(KeyType.F1, s"Device: ${device.map(_.name).getOrElse("Unknown")}", () ⇒ showDevicesList ).
        addAction(KeyType.F10, "Quit", window.close )
  }

  private def showDevicesList(implicit gui:WindowBasedTextGUI, window:Window) = {
    lazy val dialog = new ActionListDialogBuilder().setTitle("Select a device").setCanCancel(true)
    val devices = Adb.devices
    if (devices.isEmpty) {
      dialog.addAction("No devices", () ⇒ {})
    } else {
      for {
        device ← Adb.devices
      } dialog.addAction(device.name, window.setComponent(createUi(Some(device))))
    }
    dialog.build().showDialog(gui)
  }

  private def alert(text:String)(implicit gui:WindowBasedTextGUI) = {
    new MessageDialogBuilder().setTitle(title).setText(text).addButton(MessageDialogButton.OK).build().showDialog(gui)
  }
}

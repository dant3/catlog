package com.github.dant3.catlog.ui

import com.github.dant3.catlog.Implicits
import com.github.dant3.catlog.adb.{Device, Adb}
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.gui2.AbstractListBox

class LogcatView(size: TerminalSize, val device:Option[Device] = None) extends AbstractListBox[String, LogcatView](size) with Implicits {
  val logcatProcess = startLogcat(addItem)


  override def addItem(item:String):Unit = {
    super.addItem(item)
    setSelectedIndex(getItemCount - 1)
  }

  private def startLogcat(reader:String ⇒ Unit) = device match {
    case Some(dev) ⇒ Adb.logcat(dev)(reader)
    case None ⇒ Adb.logcat(reader)
  }
}

package com.github.dant3.catlog.ui

import com.googlecode.lanterna.TextColor.ANSI
import com.googlecode.lanterna.gui2.TextGUI.Listener
import com.googlecode.lanterna.gui2._
import com.googlecode.lanterna.input.{KeyStroke, KeyType}

import scala.collection.mutable

class Toolbar(gui:TextGUI) extends Panel {
  private val actions = mutable.Map[KeyType,(String, () ⇒ Unit)]()

  this.setLayoutManager(new LinearLayout(Direction.HORIZONTAL))

  gui.addListener(new Listener {
    override def onUnhandledKeyStroke(textGUI: TextGUI, keyStroke: KeyStroke): Boolean = handleKeyStroke(keyStroke)
  })

  private def handleKeyStroke(keyStroke:KeyStroke):Boolean = actions.get(keyStroke.getKeyType) match {
    case Some((_, action)) ⇒ action(); true
    case None ⇒ false
  }


  def addAction(keyType: KeyType, name:String, action:() ⇒ Unit):Toolbar = {
    if (actions.put(keyType, (name, action)).isDefined) {
      redrawActions()
    } else {
      addComponent(actionLabel(keyType, name))
    }
    this
  }

  private def redrawActions() = {
    removeAllComponents()
    for {
      (key, (label, _)) <- actions
    } addComponent(actionLabel(key, label))
  }

  def actionLabel(keyType: KeyType, name:String):Label = {
    val label = new Label(s"$keyType ${name.capitalize}")
    label.setBackgroundColor(ANSI.BLACK)
    label.setForegroundColor(ANSI.WHITE)
    label
  }
}

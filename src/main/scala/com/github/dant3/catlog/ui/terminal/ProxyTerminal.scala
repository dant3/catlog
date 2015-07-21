package com.github.dant3.catlog.ui.terminal

import java.util.concurrent.TimeUnit

import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.terminal.{ResizeListener, Terminal}
import com.googlecode.lanterna.{SGR, TerminalSize, TextColor}

class ProxyTerminal(wrappedTerminal:Terminal) extends Terminal {
  override def enterPrivateMode(): Unit = wrappedTerminal.enterPrivateMode()
  override def exitPrivateMode(): Unit = wrappedTerminal.exitPrivateMode()

  override def resetColorAndSGR(): Unit = wrappedTerminal.resetColorAndSGR()
  override def disableSGR(sgr: SGR): Unit = wrappedTerminal.disableSGR(sgr)
  override def newTextGraphics(): TextGraphics = wrappedTerminal.newTextGraphics()
  override def enquireTerminal(i: Int, timeUnit: TimeUnit): Array[Byte] = wrappedTerminal.enquireTerminal(i, timeUnit)
  override def enableSGR(sgr: SGR): Unit = wrappedTerminal.enableSGR(sgr)
  override def setCursorPosition(i: Int, i1: Int): Unit = wrappedTerminal.setCursorPosition(i, i1)
  override def getTerminalSize: TerminalSize = wrappedTerminal.getTerminalSize
  override def clearScreen(): Unit = wrappedTerminal.clearScreen()
  override def setCursorVisible(b: Boolean): Unit = wrappedTerminal.setCursorVisible(b)
  override def putCharacter(c: Char): Unit = wrappedTerminal.putCharacter(c)
  override def flush(): Unit = wrappedTerminal.flush()
  override def setBackgroundColor(textColor: TextColor): Unit = wrappedTerminal.setBackgroundColor(textColor)
  override def setForegroundColor(textColor: TextColor): Unit = wrappedTerminal.setForegroundColor(textColor)

  override def addResizeListener(resizeListener: ResizeListener): Unit = wrappedTerminal.addResizeListener(resizeListener)
  override def removeResizeListener(resizeListener: ResizeListener): Unit = wrappedTerminal.removeResizeListener(resizeListener)

  override def readInput(): KeyStroke = wrappedTerminal.readInput()
  override def pollInput(): KeyStroke = wrappedTerminal.pollInput()
}

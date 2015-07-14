package com.github.dant3.catlog

import scala.language.implicitConversions

trait Implicits {
  implicit def toRunnable(block: ⇒ Any):Runnable = new Runnable {
    override def run(): Unit = block
  }
}

package com.github.dant3.catlog

import scala.util.{Success, Failure, Try}

trait Application {
  def main(args:Array[String]):Unit = Try { run(args) } match {
    case Success(_) ⇒ System.exit(0)
    case Failure(_) ⇒ System.exit(1)
  }

  def run(args:Array[String]):Unit
}

package com.github.dant3.catlog

//import com.github.dant3.catlog.util.FocusedMutable
//import rx.lang.scala.RxVar

//import scala.collection.mutable
import scala.sys.process._

abstract class LogcatReader(/*bufferSizeLimit:Int*/) {
//  private val rxSeq = RxVar(Seq.empty[String])
//  val buffer = FocusedMutable(mutable.Queue.empty[String], { queue:mutable.Queue[String] ⇒
//    rxSeq := queue.toSeq
//  })

  private val cmd = "adb logcat -vtime"
  private val logcatProcess = cmd.run(ProcessLogger(logString _))

  protected def logString(logcatLine:String):Unit /* = buffer.modify { queue ⇒
    queue.enqueue(logcatLine)
    val overLimitedNumber = queue.size - bufferSizeLimit
    if (overLimitedNumber > 0) {
      queue.drop(overLimitedNumber)
    }
  }*/

  def shutdown() = {
    logcatProcess.destroy()
  }
}
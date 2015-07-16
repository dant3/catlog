package com.github.dant3.catlog.adb

trait AdbCommands {
  import scala.sys.process._

  def logcat(device:Device)(reader: String ⇒ Unit):Process = s"adb -s ${device.id} logcat -vtime".run(ProcessLogger(reader))
  def logcat(reader: String ⇒ Unit):Process = "adb logcat -vtime".run(ProcessLogger(reader))
  def devices:List[Device] = AdbDevicesParser.parse("adb devices -l".!!)
}

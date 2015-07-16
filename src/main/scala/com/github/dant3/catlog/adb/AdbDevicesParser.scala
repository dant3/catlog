package com.github.dant3.catlog.adb

object AdbDevicesParser {
  // format: TA9911X3UW             device usb:1D111000 product:victara_repw model:XT1094 device:victara
  def parse(devicesOutput:String):List[Device] = {
    def lines = devicesOutput.lines.toList.tail
    for {
      line ← lines
      if !line.trim.isEmpty
      device = parseDeviceInfo(line)
      if device.isDefined
    } yield device.get
  }

  private def parseDeviceInfo(deviceString:String):Option[Device] = {
    val separator = " device "
    lazy val str = deviceString.trim
    lazy val separatorIndex = str.indexOf(separator)
    if (separatorIndex > 0) {
      val (deviceID, deviceSpecs) = str.splitAt(separatorIndex)
      val modelHeadline = "model:"
      lazy val afterModel = deviceSpecs.splitAt(deviceSpecs.indexOf(modelHeadline))._2
      lazy val model = afterModel.substring(modelHeadline.length, afterModel.indexOf(' '))
      Some(Device(deviceID.trim, model))
    } else {
      None
    }
  }
}

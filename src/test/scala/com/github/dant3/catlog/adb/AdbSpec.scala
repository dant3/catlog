package com.github.dant3.catlog.adb

import org.scalatest.{Matchers, WordSpec}

class AdbSpec extends WordSpec with Matchers {
  val sampleInput =
    """List of devices attached
      |TA9911X3UW             device usb:1D111000 product:victara_repw model:XT1094 device:victara
      |44a6b869               device usb:1D112000 product:hltexx model:SM_N9005 device:hlte
      |
    """.stripMargin

  "AdbDevicesParser" should {
    "be able to parse devices output" in {
      val devices = AdbDevicesParser.parse(sampleInput)
      devices should (contain(Device("TA9911X3UW", "XT1094")) and contain(Device("44a6b869", "SM_N9005")) and have length(2))
    }
  }
}

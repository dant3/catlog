name := "catlog"

version := "1.0"

scalaVersion := "2.11.6"

val libs = new {
  val rxScala = "io.reactivex" %% "rxscala" % "0.25.0"

  val scalaTest = "org.scalatest" %% "scalatest" % "2.2.4" % "test"
  val lanterna = "com.googlecode.lanterna" % "lanterna" % "3.0.0-beta1"

  def toSeq = Seq(rxScala, lanterna, scalaTest)
}

resolvers += "Central snapshots" at "http://repository.apache.org/snapshots/"

libraryDependencies ++= libs.toSeq

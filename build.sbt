name := "catlog"

version := "1.0"

scalaVersion := "2.11.6"

Plugins.apply

val libs = new {
  val rxScala = "io.reactivex" %% "rxscala" % "0.25.0"

  val scalaTest = "org.scalatest" %% "scalatest" % "2.2.4" % "test"
  val lanterna = "com.github.avl42" % "lanterna" % "63991beae50a"//"c4eeece81a"

  def repos = Seq(
    "Central snapshots" at "http://repository.apache.org/snapshots/",
    "jitpack" at "https://jitpack.io"
  )

  def toSeq = Seq(rxScala, lanterna, scalaTest)
}


resolvers ++= libs.repos

libraryDependencies ++= libs.toSeq

import com.github.retronym.SbtOneJar._
import sbt.Setting

object Plugins {
  def apply:Seq[Setting[_]] = oneJarSettings
}

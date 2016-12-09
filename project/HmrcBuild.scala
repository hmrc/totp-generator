import sbt.Keys._
import sbt._

import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.versioning.SbtGitVersioning


object HmrcBuild extends Build {

  val appName = "totp-generator"

  lazy val microservice = Project(appName, file("."))
    .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning)
    .settings(
      scalaVersion := "2.11.7",
      libraryDependencies ++= AppDependencies(),
      crossScalaVersions := Seq("2.11.7"),
      resolvers := Seq(
        Resolver.bintrayRepo("hmrc", "releases"),
        "typesafe-releases" at "http://repo.typesafe.com/typesafe/releases/"
      )
    )
}

private object AppDependencies {

  val compile = Seq(
    "commons-codec" % "commons-codec" % "1.10"
  )

  val testDependencies = Seq(
    "org.scalatest" %% "scalatest" % "2.2.6" % "test",
    "org.pegdown" % "pegdown" % "1.5.0" % "test"
  )

  def apply() = compile ++ testDependencies
}

object Developers {

  def apply() = developers := List[Developer]()
}

import sbt.Keys._
import sbt._
import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.SbtArtifactory
import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion
import uk.gov.hmrc.versioning.SbtGitVersioning
import uk.gov.hmrc.SbtArtifactory.autoImport.makePublicallyAvailableOnBintray

val appName = "totp-generator"
lazy val deps: Seq[ModuleID] = compile ++ test

ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.6.0"

inThisBuild(
  List(
    scalaVersion := "2.12.12",
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision
  )
)

lazy val microservice = Project(appName, file("."))
  .settings(
    scalaVersion := "2.12.12",
    majorVersion := 0,
    makePublicallyAvailableOnBintray := true,
    libraryDependencies ++= deps,
    resolvers += "typesafe-releases" at "https://repo.typesafe.com/typesafe/releases/"
  )

scalacOptions ++= Seq("-Xlint:unused")

val compile: Seq[ModuleID] = Seq(
  "commons-codec" % "commons-codec" % "1.10"
)

val test: Seq[ModuleID] = Seq(
  "org.scalatest" %% "scalatest" % "3.0.2" % "test",
  "org.pegdown" % "pegdown" % "1.5.0" % "test"
)

import sbt._
import sbt.Keys._
import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion

lazy val appName = "totp-generator"

lazy val scala212 = "2.12.16"
lazy val scala213 = "2.13.12"
lazy val supportedScalaVersions = List(scala212, scala213)

lazy val deps: Seq[ModuleID] = compile ++ test

lazy val microservice = Project(appName, file("."))
  .settings(
    crossScalaVersions := supportedScalaVersions,
  )
  .settings(
    scalaVersion := scala213,
    majorVersion := 0,
    isPublicArtefact := true,
    libraryDependencies ++= deps
  )

val compile: Seq[ModuleID] = Seq(
  "commons-codec"         % "commons-codec"   % "1.10"
)

val test: Seq[ModuleID] = Seq(
  "org.scalatest"         %%  "scalatest"     % "3.2.17",
  "com.vladsch.flexmark"  %   "flexmark-all"  % "0.62.2",
  "org.pegdown"           %   "pegdown"       % "1.5.0"
).map(_ % Test)

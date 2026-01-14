import sbt._
import sbt.Keys._
import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion

ThisBuild / scalaVersion := "3.3.7"
ThisBuild / majorVersion := 1

ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

lazy val microservice = Project("totp-generator", file("."))
  .disablePlugins(JUnitXmlReportPlugin)
  .settings(
    isPublicArtefact := true,
    libraryDependencies ++= LibraryDependencies.compile ++ LibraryDependencies.test
  )

commands ++= Seq(
  Command.command("run-all-tests") { state => "test" :: state },

  Command.command("clean-and-test") { state => "clean" :: "compile" :: "run-all-tests" :: state },

  // Coverage does not need compile !
  Command.command("pre-commit") { state => "clean" :: "scalafmtAll" :: "scalafixAll" :: "coverage" :: "run-all-tests" :: "coverageOff" :: "coverageAggregate" :: state }
)

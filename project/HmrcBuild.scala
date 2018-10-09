/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import sbt.Keys._
import sbt._
import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.SbtArtifactory
import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion
import _root_.play.sbt.PlayScala
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin
import uk.gov.hmrc.versioning.SbtGitVersioning

object HmrcBuild extends Build {

  val appName = "totp-generator"

  lazy val microservice = Project(appName, file("."))
    .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory, PlayScala, SbtDistributablesPlugin)
    .settings(
      scalaVersion := "2.11.11",
      majorVersion := 0,
      libraryDependencies ++= AppDependencies(),
      crossScalaVersions := Seq("2.11.11"),
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

resolvers += Resolver.url("hmrc-sbt-plugin-releases",
  url("https://dl.bintray.com/hmrc/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.1")
addSbtPlugin("uk.gov.hmrc" % "sbt-auto-build" % "1.8.0")
addSbtPlugin("uk.gov.hmrc" % "sbt-bobby" % "0.32.0")
addSbtPlugin("uk.gov.hmrc" % "sbt-git-versioning" % "0.9.0")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")

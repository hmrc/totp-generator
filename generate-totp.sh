#!/bin/bash

if [ ! -f target/scala-2.12/totp-generator-assembly-*.jar ]; then
  echo "NOTE target/scala-2.12/totp-generator-assembly-*.jar does not exist, building it with sbt"
  sbt clean assembly
fi

java -cp target/scala-2.12/totp-generator-assembly-*.jar uk.gov.hmrc.totp.TotpGenerator $1


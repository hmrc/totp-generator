#!/bin/bash

java -cp target/scala-2.12/totp-generator-assembly-*.jar uk.gov.hmrc.totp.TotpGenerator $1


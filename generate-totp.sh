#!/bin/bash

scala -cp target/scala-2.11/totp-generator-assembly-*.jar uk.gov.hmrc.totp.TotpGenerator $1

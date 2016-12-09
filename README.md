
# TOTP Generator

[![Build Status](https://travis-ci.org/hmrc/totp-generator.svg?branch=master)](https://travis-ci.org/hmrc/totp-generator) [ ![Download](https://api.bintray.com/packages/hmrc/releases/totp-generator/images/download.svg) ](https://bintray.com/hmrc/releases/totp-generator/_latestVersion)

This is a placeholder README.md for a new repository

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").


***

# Instructions

Tool that generates a TOTP (timed-based-one-time password) from a secret.
It uses the HMAC-SHA512 encryption

### Packaging

Run `sbt clean assembly`

### Running

./generate-totp.sh [TOTP_SECRET]

example: `./generate-totp.sh BZDHSGCIGQXRWVQX`


***

# More info:
* [TOTP algorithm](https://en.wikipedia.org/wiki/Time-based_One-time_Password_Algorithm)
* [TOTP algorithm specifications](https://tools.ietf.org/html/rfc6238)
* [HOTP algorithm specifications](https://tools.ietf.org/html/rfc4226)
* [Oauth 2.0 specifications](https://tools.ietf.org/html/rfc6749)

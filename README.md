
# TOTP Generator

[![Build Status](https://travis-ci.org/hmrc/totp-generator.svg?branch=master)](https://travis-ci.org/hmrc/totp-generator) [ ![Download](https://api.bintray.com/packages/hmrc/releases/totp-generator/images/download.svg) ](https://bintray.com/hmrc/releases/totp-generator/_latestVersion)

This is a placeholder README.md for a new repository

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").


***

# Instructions

This tool generates a 8-digits TOTP code (Timed Based One-time Password) from a shared secret key.
Thd implementation uses a HMAC (Hash-based Message Authentication Code) SHA-512 encryption algorithm.
We based our work on the Java reference implementation provided in https://tools.ietf.org/html/rfc6238#appendix-A.
This tool is built for testing purposes only, it is not intended to be part of a production stack.

### Packaging

The Jar file containing the binaries can be build locally by running `sbt clean assembly`
You could also find it here:


### Running

./generate-totp.sh [TOTP_SECRET]

example: `./generate-totp.sh BZDHSGCIGQXRWVQX`


***


[Ciao](#ciao-bella)

# Useful links:

* [HMAC](https://en.wikipedia.org/wiki/Hash-based_message_authentication_code)
* [TOTP algorithm](https://en.wikipedia.org/wiki/Time-based_One-time_Password_Algorithm)
* [TOTP algorithm specifications](https://tools.ietf.org/html/rfc6238)
* [TOTP algorithm Java implementation](https://tools.ietf.org/html/rfc6238#appendix-A)
* [HOTP algorithm specifications](https://tools.ietf.org/html/rfc4226)

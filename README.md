# TOTP Generator

[![Build Status](https://travis-ci.org/hmrc/totp-generator.svg?branch=master)](https://travis-ci.org/hmrc/totp-generator) [ ![Download](https://api.bintray.com/packages/hmrc/releases/totp-generator/images/download.svg) ](https://bintray.com/hmrc/releases/totp-generator/_latestVersion)


*_IMPORTANT_*: This tool is built for testing purposes only, it is not intended to be part of a production stack.

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").

### Attribution

The code in this project is based on Mark Lister's project [Scala TOTP Auth](https://github.com/marklister/scala-totp-auth)

# Instructions

This tool generates a 8-digits TOTP code (Timed Based One-Time Password) from a shared secret key.
<br />
Each TOTP code is valid for a time window of 30 seconds.
<br />
The implementation uses a HMAC (Hash-based Message Authentication Code) SHA-512 encryption algorithm.
<br />

### Packaging

You can find the Jar containing the binaries in here:
* https://bintray.com/hmrc/releases/totp-generator
* https://hmrc.bintray.com/releases/uk/gov/hmrc/totp-generator_2.11/

Alternatively, the Jar containing the binaries can be built locally by running `sbt clean assembly`


### Running

./generate-totp.sh [TOTP_SECRET]

For example: `./generate-totp.sh BZDHSGCIGQXRWVQX`


### Unit tests
```
sbt test
```


***

# Useful links:

* [HMAC](https://en.wikipedia.org/wiki/Hash-based_message_authentication_code)
* [TOTP algorithm](https://en.wikipedia.org/wiki/Time-based_One-time_Password_Algorithm)
* [TOTP algorithm specifications](https://tools.ietf.org/html/rfc6238)
* [TOTP algorithm Java implementation](https://tools.ietf.org/html/rfc6238#appendix-A)
* [HOTP algorithm specifications](https://tools.ietf.org/html/rfc4226)

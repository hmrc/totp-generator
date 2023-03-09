# Scala TOTP generator for HMRC API Platform

## Requirements

* [sbt](https://www.scala-sbt.org/)
* JDK 8 (newer versions should work but have not been tested)

## Usage

To generate an 8 digit TOTP code using the TOTP secret `ABCDEFGHIJKLMNOP`
```
./generate-totp.sh ABCDEFGHIJKLMNOP
```

The output will look like:
```
TOTP: 38079726
```
This is showing:
* the TOTP code for now. This can be used to generate an OAuth access token.

## Attribution

The code in this project is based on Mark Lister's project [Scala TOTP Auth](https://github.com/marklister/scala-totp-auth)

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").

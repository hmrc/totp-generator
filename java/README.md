# Java TOTP generator for HMRC API Platform

## Requirements

* JDK 8 (newer versions should work but have not been tested)

## Usage

To generate an 8 digit TOTP code using the TOTP secret `ABCDEFGHIJKLMNOP`
```
./gradlew run --args ABCDEFGHIJKLMNOP
```

The output will look like:
```
Using secret = ABCDEFGHIJKLMNOP
Tue Jun 01 00:00:00 BST 2021 = 87779282
Wed Jan 05 16:48:03 GMT 2022 = 60985644
```
This is showing:
* the TOTP code that would be generated on 1st June 2021 at 00:00. This is useful to test that the TOTP generator is creating the correct code.
* the TOTP code for now. This can be used to generate an OAuth access token.

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").

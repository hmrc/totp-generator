# Python TOTP generator for HMRC API Platform

## Requirements

* Python 3

## Setup

Install dependencies:
```
pip3 install pyotp
```

## Usage

For help:
```
totp -h
```

To generate an 8 digit TOTP code using the TOTP secret `ABCDEFGHIJKLMNOP`
```
python3 totp.py ABCDEFGHIJKLMNOP
```
or
```
./totp.py ABCDEFGHIJKLMNOP
```
The output will look like:
```
2021-06-01T00:00:00.000    = 87779282
2022-01-05T16:41:11.490073 = 66025830
```
This is showing:
* the TOTP code that would be generated on 1st June 2021 at 00:00. This is useful to test that the TOTP generator is creating the correct code.
* the TOTP code for now. This can be used to generate an OAuth access token.

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").

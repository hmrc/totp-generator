#!/usr/bin/env python3

import argparse
import pyotp
import hashlib
import datetime


def main():

    parser = argparse.ArgumentParser(description='Generate TOTP codes')
    parser.add_argument('secret', metavar='SECRET', help='The secret used to generate the TOTP code')
    parser.add_argument('-d', '--digits', default=8, type=int, help='Number of digits in the generated code')
    args = parser.parse_args()

    totp = pyotp.TOTP(args.secret, digits=args.digits, digest=hashlib.sha512)

    fixed_datetime = '2021-06-01T00:00:00.000'
    print("{}    = {}".format(fixed_datetime, totp.at(datetime.datetime.fromisoformat(fixed_datetime))))

    print("{} = {}".format(datetime.datetime.now().isoformat(),  totp.now()))


if __name__ == '__main__':
    main()

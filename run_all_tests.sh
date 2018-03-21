#!/usr/bin/env bash
sbt clean compile coverage test coverageOff coverageReport

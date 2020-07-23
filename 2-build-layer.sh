#!/bin/bash
set -eo pipefail
gradle -q packageLibs
mv build/distributions/java-test-lambda.zip build/java-test-lambda.zip
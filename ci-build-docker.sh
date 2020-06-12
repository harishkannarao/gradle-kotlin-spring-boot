#!/bin/sh

# Make the script to abort if any command fails
set -e

# Print the commands as it is executed. Useful for debugging
set -x

docker build --pull -t com.harishkannarao/kotlin-spring-boot:latest -f application/Dockerfile application/build/libs
#!/bin/sh

# Make the script to abort if any command fails
set -e

# Print the commands as it is executed. Useful for debugging
set -x

docker run --rm -d --name kotlin-spring-boot -p '8080:8080' com.harishkannarao/kotlin-spring-boot:latest
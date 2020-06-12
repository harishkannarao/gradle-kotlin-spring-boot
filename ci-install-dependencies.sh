#!/bin/sh

# Make the script to abort if any command fails
set -e

# Print the commands as it is executed. Useful for debugging
set -x

sudo apt-get -qq update
sudo apt-get -qq install -y jq

java --version
docker --version
docker-compose --version
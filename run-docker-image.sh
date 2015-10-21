#!/usr/bin/env bash
# This script builds and runs Docker image containing the dropwizard-sandbox application
# You must run 'mvn clean package' first to create the fat JAR
# Make sure 8080 is forwarded in the VirtualBox boot2docker VM on OSX

docker build -t dropwizard-sandbox .
docker rm -f dropwizard-sandbox
docker run -d -p 8080:8080 --name dropwizard-sandbox  dropwizard-sandbox java -jar /root/app.jar server /root/app.yml
docker ps
echo "$(tput setaf 6)Enter this URL in your browser: $(tput setaf 2)http://localhost:8080/game/123$(tput sgr 0)"

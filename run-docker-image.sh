#!/usr/bin/env bash
# This script builds and runs Docker image containing the dropwizard-sandbox application
# You must run 'mvn clean package' first to create the fat JAR

echo "$(tput setaf 3)If using OSX rather than Linux make sure port 8080 and 8081 are forwarded to port 8080 and 8081 in the VirtualBox docker VM$(tput sgr 0)"
docker build -t dropwizard-sandbox .
docker rm -f dropwizard-sandbox
docker run -d -p 8080:8080 -p 8081:8081 --name dropwizard-sandbox  dropwizard-sandbox java -jar /root/app.jar server /root/app.yml
docker ps
echo "$(tput setaf 6)To retrieve a game enter this URL in your browser (or cmd click in osx):"
echo "$(tput setaf 2)http://localhost:8080/games/1$(tput sgr 0)"

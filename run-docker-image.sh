#!/usr/bin/env bash
# This script builds and runs Docker image containing the dropwizard-sandbox application
# You must run 'mvn clean package' first to create the fat JAR

echo "$(tput setaf 3)Make sure port 8080 is forwarded to port 8080 in the VirtualBox docker VM on OSX$(tput sgr 0)"
docker build -t dropwizard-sandbox .
docker rm -f dropwizard-sandbox
docker run -d -p 8080:8080 --name dropwizard-sandbox  dropwizard-sandbox java -jar /root/app.jar server /root/app.yml
docker ps
echo "$(tput setaf 6)To retrieve a game enter this URL in your browser (or cmd click in osx): $(tput setaf 2)http://localhost:8080/games/1$(tput sgr 0)"

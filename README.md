dropwizard-sandbox
====

A simple [Dropwizard](http://www.dropwizard.io/) project for experimenting with the framework. 
Includes a [Docker](https://www.docker.com/) container that the application can be run in.

Build
----
```mvn clean package```

Run
----
```java -jar target/dropwizard-sandbox-1.0-SNAPSHOT.jar server dropwizard-sandbox.yml```

Endpoints
----
- http://localhost:8080/game/1
- http://localhost:8081/

Run in Docker container
----
```./run-docker-image.sh```



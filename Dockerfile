FROM java:8 

ADD ./target/dropwizard-sandbox-1.0-SNAPSHOT.jar /root/app.jar
ADD ./dropwizard-sandbox.yml /root/app.yml

EXPOSE 8080

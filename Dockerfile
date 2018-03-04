FROM java:7
VOLUME /tmp
ADD target/api-0.1.0.jar /app.jar
EXPOSE 80
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

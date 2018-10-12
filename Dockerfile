FROM openjdk:8
MAINTAINER Marcus Lin <linfaimom@gmail.com>
ADD JiusanStar.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

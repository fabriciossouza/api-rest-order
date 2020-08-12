FROM openjdk:8u181-jre-alpine3.8
ADD /target/*.jar /
RUN mv /*.jar app-rest.jar
ENTRYPOINT exec java -jar app-rest.jar

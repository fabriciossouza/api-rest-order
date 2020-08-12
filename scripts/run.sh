#!/bin/sh +x
docker rm -f api-rest;
PROJECT_VERSION=$(ls target/*.jar | cut -d'-' -f 3)
echo "PROJECT VERSION: api-rest-${PROJECT_VERSION}"

docker run -d --name api-rest -p 8080:8080 api-rest:${PROJECT_VERSION}

#!/bin/sh +x
PROJECT_VERSION=$(ls target/*.jar | cut -d'-' -f 3)
echo "PROJECT VERSION: api-rest-${PROJECT_VERSION}"

docker build . -t api-rest:${PROJECT_VERSION}

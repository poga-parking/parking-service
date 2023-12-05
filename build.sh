#!/bin/zsh

./gradlew bootJar
docker build --tag innoboys/parking-service:0.0.1 -f DockerfileJar .

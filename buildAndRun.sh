#!/bin/sh
mvn clean package && docker build -t JavaLab/EsportWeb .
docker rm -f EsportWeb || true && docker run -d -p 9080:9080 -p 9443:9443 --name EsportWeb JavaLab/EsportWeb
@echo off
call mvn clean package
call docker build -t JavaLab/EsportWeb .
call docker rm -f EsportWeb
call docker run -d -p 9080:9080 -p 9443:9443 --name EsportWeb JavaLab/EsportWeb
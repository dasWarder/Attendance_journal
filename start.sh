#!/bin/bash

docker-compose down

mvn clean package

docker-compose --env-file .env up -d


#!/bin/bash

mvn clean package -P prod -D  maven.test.skip=true

cd ./tecwealth-web
mkdir ./target/tecwealth-web
mkdir ./target/tecwealth-web/logs
cp -rf ./target/*.jar ./target/tecwealth-web
cp -rf ./script/* ./target/tecwealth-web
cd -
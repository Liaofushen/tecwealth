#!/bin/bash

mvn clean package -P prod -D  maven.test.skip=true

cd ./tecwealth-web
mkdir ./target/tecwealth-web
mkdir ./target/tecwealth-web/logs
mkdir ./target/tecwealth-web/config
cp -rf ./target/*.jar ./target/tecwealth-web
cp -rf ./script/* ./target/tecwealth-web
cp -rf ./target/classes/*.yml ./target/tecwealth-web/config
cp -rf ./target/classes/*.xml ./target/tecwealth-web/config
cd -
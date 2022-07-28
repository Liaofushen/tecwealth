#!/bin/bash

set -e
ENV=$1

export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
# export PATH=${JAVA_HOME}/bin:$PATH
export CLASSPATH=.:${JAVA_HOME}/lib/tools.jar:${JAVA_HOME}/lib/rt.jar

mvn clean package -Dmaven.test.skip=true -P$ENV

rm -rf tecwealth
mkdir tecwealth
mkdir tecwealth/config
mkdir tecwealth/logs

cp -rf ./web/target/*.jar ./tecwealth/
cp -rf ./web/target/classes/*.yml ./tecwealth/config
cp -rf ./web/target/classes/*.xml ./tecwealth/config
cp -rf ./sms.sh ./tecwealth

rm -rf ~/tecwealth
cp -rf tecwealth ~/

rm -rf tecwealth
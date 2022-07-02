#!/bin/bash

set -e

export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
export PATH=${JAVA_HOME}/bin:$PATH
export CLASSPATH=.:${JAVA_HOME}/lib/tools.jar:${JAVA_HOME}/lib/rt.jar

mvn clean package -Dmaven.test.skip=true

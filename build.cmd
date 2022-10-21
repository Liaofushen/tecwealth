call C:\home\softwares\apache-maven-3.8.6\bin\mvn.cmd -f pom.xml clean package -P prod -D maven.test.skip=true

cd ./tecwealth-web
mkdir .\target\tecwealth-web
xcopy .\target\*.jar .\target\tecwealth-web\
xcopy .\script\* .\target\tecwealth-web\
cd ..
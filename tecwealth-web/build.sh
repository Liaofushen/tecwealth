mvn -f pom.xml clean package -P prod -D maven.test.skip=true
mkdir .\target\tecwealth-web
cp -f .\target\*.jar .\target\tecwealth-web
cp -f .\script\* .\target\tecwealth-web
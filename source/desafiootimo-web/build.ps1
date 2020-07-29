mvn -DskipTests=true clean package
rm -Recurse ..\..\docker\images\app\*.jar
cp target\desafiootimo-web-0.0.1-SNAPSHOT.jar ..\..\docker\images\app\app.jar
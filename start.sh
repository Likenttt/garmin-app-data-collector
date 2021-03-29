rm -rf target
git pull
mv ~/application.properties src/main/resources/
mvn package -Dtest.skip=true
rm nohup.out
kill `ps -ef | grep jump-data-reciever-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
nohup java -jar target/jump-data-reciever-0.0.1-SNAPSHOT.jar &!
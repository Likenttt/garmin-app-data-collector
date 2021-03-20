kill $(ps -ef | grep "jump-data-reciever-0.0.1-SNAPSHOT.jar" | grep -v grep | awk '{print $2}')
mvn package
nohup java -jar target/jump-data-reciever-0.0.1-SNAPSHOT.jar &!
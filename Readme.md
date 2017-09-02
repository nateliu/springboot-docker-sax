./gradlew build buildDocker

docker run -p 8080:8080 -t springboot-docker-sax:0.0.1-SNAPSHOT

docker cp /var/lib/docker/student.xml 01ba3c3f2e7d:/tmp/student.xml

http://localhost:8080/sax.html

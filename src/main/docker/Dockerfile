FROM openjdk:11
VOLUME /tmp
ADD demo-0.0.1-SNAPSHOT.war app.war
RUN bash -c 'touch /app.war'
ENTRYPOINT [ "java","-jar","/app.war" ]
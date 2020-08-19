FROM openjdk:11
COPY AdminProject-datastoreaccount.json /Users/pu1et/Desktop/wallet/key/
VOLUME /tmp
ADD demo-0.0.1-SNAPSHOT.war app.war
RUN bash -c 'touch /app.war'
ENTRYPOINT [ "java","-jar","/app.war" ]
FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
EXPOSE 8090
ARG JAR_FILE=target/m3.jar
ADD ${JAR_FILE} signup.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar signup.jar" ]
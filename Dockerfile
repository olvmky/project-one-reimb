FROM java:8
ARG JAR_FILE=target/*.jar
WORKDIR /opt
ADD target/my-app-1.0-SNAPSHOT.jar /opt
COPY ${JAR_FILE} app.jar

CMD ["java", "-jar", "opt/my-app-1.0-SNAPSHOT.jar", , "/app.jar"]

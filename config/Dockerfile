FROM openjdk:21

ADD ./target/config-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/config-0.0.1-SNAPSHOT.jar"]

EXPOSE 8888
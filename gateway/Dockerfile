FROM openjdk:21

ADD ./target/gateway-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/gateway-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080
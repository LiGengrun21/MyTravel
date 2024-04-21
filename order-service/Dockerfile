FROM openjdk:21

ADD ./target/order-service-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/order-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 7003
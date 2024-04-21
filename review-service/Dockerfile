FROM openjdk:21

ADD ./target/review-service-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/review-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 7004
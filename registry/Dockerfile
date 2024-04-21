FROM openjdk:21

ADD ./target/registry.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/registry.jar"]

EXPOSE 8762
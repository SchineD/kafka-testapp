FROM maven:3-openjdk-14 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package
FROM openjdk:14-alpine
WORKDIR /main
COPY --from=MAVEN_BUILD /build/target/kafka-0.0.1-SNAPSHOT.jar /main/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kafka-0.0.1-SNAPSHOT.jar"]
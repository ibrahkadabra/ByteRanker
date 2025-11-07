FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY services/judge-worker-java /app
RUN ./gradlew clean bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]

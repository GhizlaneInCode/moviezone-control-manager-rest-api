FROM maven:3.9.6-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11
WORKDIR /app
COPY --from=build /app/target/movieapp.jar /app/myMovieApp.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/myMovieApp.jar"]

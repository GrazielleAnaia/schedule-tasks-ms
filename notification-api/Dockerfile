FROM gradle:8.7-jdk17-alpine AS build

WORKDIR /app

COPY . .

RUN gradle build --no-daemon


FROM amazoncorretto:17-alpine3.19-jdk

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/notification-api.jar
EXPOSE 8182
CMD ["java", "-jar", "/app/notification-api.jar"]



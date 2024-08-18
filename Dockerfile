FROM openjdk:17-jdk

WORKDIR /app

COPY target/World-Banking-Application-1.0.jar /app/World-Banking-Application.jar

EXPOSE 8080

CMD ["java", "-jar", "World-Banking-Application.jar"]
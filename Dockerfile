FROM gradle:jdk17
VOLUME /data/db
COPY build/libs/spring-app-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/home/gradle/app.jar"]

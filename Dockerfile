FROM ligaard/jdk17-gradle73
WORKDIR /app
COPY /build/libs/mars-dgs-0.0.1-SNAPSHOT.jar /app/mars-dgs.jar

EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/app/mars-dgs.jar"]
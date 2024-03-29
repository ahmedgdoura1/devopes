FROM eclipse-temurin:17-jdk-alpine
RUN apk update && apk add curl
RUN curl -o app.jar http://192.168.33.10:8081/repository/maven-snapshots/tn/esprit/DevOps_Project/2.1/DevOps_Project-2.1.jar -L
COPY DevOps_Project-2.1.jar /DevOps_Project-2.1.jar
ENTRYPOINT ["java","-jar","/DevOps_Project-2.1.jar"]
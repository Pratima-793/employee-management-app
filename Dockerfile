FROM openjdk:24-jdk

COPY target/crudapp.jar .

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "crudapp.jar"]


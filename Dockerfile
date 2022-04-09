FROM openjdk:18
COPY build/libs/servicebackend-0.0.1-SNAPSHOT.jar servicebackend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/servicebackend-0.0.1-SNAPSHOT.jar"]
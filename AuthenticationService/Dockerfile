FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/AuthenticationServiceApp-0.0.1-SNAPSHOT.jar authenticationServiceApp.jar
ENTRYPOINT ["java", "-jar", "authenticationServiceApp.jar"]
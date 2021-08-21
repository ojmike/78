FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/DiscoveryServiceApp-0.0.1-SNAPSHOT.jar discoveryServiceApp.jar
ENTRYPOINT ["java", "-jar", "discoveryServiceApp.jar"]
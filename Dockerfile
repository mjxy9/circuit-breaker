FROM amazoncorretto:17-alpine-jdk

WORKDIR /rede

COPY target/circuitbreaker.jar ./

EXPOSE 8080

CMD ["java", "-jar", "circuitbreaker.jar"]
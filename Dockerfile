FROM openjdk:8-jdk-alpine
COPY target/TipoCambio.jar /TipoCambio.jar
EXPOSE 8080
CMD ["java", "-jar", "/TipoCambio.jar"]
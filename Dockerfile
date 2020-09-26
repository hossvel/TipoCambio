FROM openjdk:8-jre-alpine
RUN apk add --no-cache curl
COPY target/TipoCambio.jar /TipoCambio.jar
EXPOSE 8080
CMD ["java", "-jar", "/TipoCambio.jar"]




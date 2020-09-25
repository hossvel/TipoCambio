FROM java:8-jdk-alpine
WORKDIR /
ADD target/TipoCambio.jar TipoCambio.jar
EXPOSE 8080
CMD java - jar TipoCambio.jar
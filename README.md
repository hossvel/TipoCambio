# TipoCambio
tipo de cambio de soles a dolares o dolares a soles

# execute in container
sudo docker exec -it e1d7475879fc /bin/bash

# get into  container 
curl localhost:8080/api/tipocambio
 
# Run container sonarqube
  docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest

# Sonarqube
mvn clean package sonar:sonar
mvn sonar:sonar -Dsonar.login=<your-token>

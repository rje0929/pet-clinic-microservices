#!/bin/bash

mvn -file pet-clinic-config-server/pom.xml clean install
mvn -file pet-clinic-eureka-server/pom.xml clean install
mvn -file pet-clinic-zuul-server/pom.xml clean install
mvn -file pet-clinic-owner-service/pom.xml clean install
mvn -file pet-clinic-visits-service/pom.xml clean install
mvn -file pet-clinic-vets-service/pom.xml clean install
mvn -file spring-petclinic-master/pom.xml clean install
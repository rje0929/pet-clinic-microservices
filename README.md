# Spring PetClinic Microservices Project

This is my Microservices implementation of the famous Spring PetClinic Project.

## A General Overview of the Spring PetClinic Project using Diagrams

[View Diagrams](https://speakerdeck.com/michaelisvy/spring-petclinic-sample-application)

### Project Overview

This is a [Spring Boot](https://spring.io/guides/gs/spring-boot) project that is built using [Maven](https://spring.io/guides/gs/maven/).  It uses the following Spring Cloud sub projects:

* Spring Cloud Config
* Eureka Discovery Service
* Zuul API Gateway
* Hystrix
* Feign Clients

This project is divided into six different microservices and a web application interface.  You can start each of the services locally or you can use docker-compose to build a set of [Docker](https://www.docker.com/) images to run the entire application.

First, you'll need to pull down the project:

```
git clone https://github.com/rje0929/pet-clinic-microservices
```

If you don't want to use Docker, you'll have to start each one of the services manually.  I'd recommend starting them in them in this order:

* Config Service
* Eureka Service
* Zuul Service
* Owner Service, Visits Service, Vets Service can be started in any order
* Web Interface

If you're using Docker, run the following commands:

```
cd pet-clinic-microservice
./build_images.sh
docker-compose up
```

The build images script may take several minutes to run.  You may also see some exceptions in the logs while its building.  These are the result of each service looking for the Eureka service (that's not currently running) while they are building.  You may also see a few exceptions when the containers come up as they wait for the Eureka service and the Config service to start. To my knowledge, the docker-compose utility does not have a graceful way of starting containers in such a way that a container can wait for the successful start of dependent containers. 

NOTE:  I had to set my Docker memory to 4 GB to avoid OutOfMemory issues.  The standard is 2 GB.  This can be changed in the Docker -> Preferences -> Advanced -> Memory section of the Docker app.

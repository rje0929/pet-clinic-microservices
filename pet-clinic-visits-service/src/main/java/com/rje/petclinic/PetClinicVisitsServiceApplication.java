package com.rje.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class PetClinicVisitsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetClinicVisitsServiceApplication.class, args);
    }
}

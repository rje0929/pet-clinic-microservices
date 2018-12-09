package com.rje.petclinicconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class PetClinicConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetClinicConfigServerApplication.class, args);
    }
}

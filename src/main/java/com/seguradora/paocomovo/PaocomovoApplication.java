package com.seguradora.paocomovo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.seguradora.paocomovo.repository")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PaocomovoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaocomovoApplication.class, args);
    }

}

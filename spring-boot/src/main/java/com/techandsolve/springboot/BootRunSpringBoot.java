package com.techandsolve.springboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;


@SpringBootApplication
@EnableWebFlux
@ComponentScan("com.techandsolve")
@EnableJpaRepositories("com.techandsolve.springboot.repository")
@EntityScan("com.techandsolve.domain")
public class BootRunSpringBoot {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BootRunSpringBoot.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }


}

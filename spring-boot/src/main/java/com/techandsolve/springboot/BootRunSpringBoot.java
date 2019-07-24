package com.techandsolve.springboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class BootRunSpringBoot {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BootRunSpringBoot.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @RestController
    public static class ApiController {
        @GetMapping("/")
        public String say(){
            return "Hola Mundo";
        }
    }

}

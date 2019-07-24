package com.techandsolve.micronaut;


import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.runtime.Micronaut;

public class BootRunMicronaut {
    public static void main(String[] args) {
        Micronaut.run(BootRunMicronaut.class);
    }

    @Controller("/")
    public static class ApiController {

        @Get
        public String say() {
            return "Hola mundo!";
        }
    }

}

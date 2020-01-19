package com.techandsolve.micronaut;


import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.runtime.Micronaut;

public class BootRunMicronaut {
    public static void main(String[] args) {
        Micronaut.run(BootRunMicronaut.class);
    }

    @Controller("/hello")
    public static class TestController {

        @Get(value = "/", produces = MediaType.TEXT_PLAIN)
        String getTest() {
            return "some string";
        }
    }
}

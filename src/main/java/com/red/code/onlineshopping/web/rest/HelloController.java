package com.red.code.onlineshopping.web.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.red.code.onlineshopping.service.dto.HelloResponse;

@RestController
public class HelloController {

    @GetMapping(value = "/hello/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public HelloResponse helloWorld(@PathVariable("name") String name) {
        return new HelloResponse("Hello " + name);
    }

    @GetMapping(value = "/gello")
    public String Funct() {
        return "ooo";
    }
}

package com.anshul.ecom.controller;

import org.springframework.web.bind.annotation.RestController;

import com.anshul.ecom.request.Body;
import com.anshul.ecom.response.Response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class GreetingsController {
    
    @GetMapping("/hello")    
    public Response hello() {
        return new Response("Hello World!");
    }

    @GetMapping("/hellowithparam/{name}")    
    public Response helloWithParam(@PathVariable String name) {
        return new Response("Hello, "+name);
    }

    @PostMapping("/bye")
    public Response bye(@RequestBody Body reqBody) {
        return new Response("Bye! "+reqBody.getMesg());
    }
}
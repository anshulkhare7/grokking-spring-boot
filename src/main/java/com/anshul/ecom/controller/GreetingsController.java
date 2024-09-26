package com.anshul.ecom.controller;

import org.springframework.web.bind.annotation.RestController;

import com.anshul.ecom.request.Body;
import com.anshul.ecom.response.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class GreetingsController {
    
    @GetMapping("/hello")    
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World!",HttpStatus.OK);
    }

    @GetMapping("/hellowithparam/{name}")    
    public ResponseEntity<String> helloWithParam(@PathVariable String name) {
        return new ResponseEntity<>("Hello, " + name, HttpStatus.OK);        
    }

    @PostMapping("/bye")
    public Response bye(@RequestBody Body reqBody) {
        return new Response("Bye! "+reqBody.getMesg());
    }
}
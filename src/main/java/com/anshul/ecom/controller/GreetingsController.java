package com.anshul.ecom.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anshul.ecom.request.Body;
import com.anshul.ecom.response.Response;


@RestController
public class GreetingsController {
    
    @GetMapping("/hello")    
    public ResponseEntity<Response> hello() {
        return new ResponseEntity<>(new Response("Hello World!"), HttpStatus.OK);
    }

    @GetMapping("/hellowithparam/{name}")    
    public ResponseEntity<Response> helloWithParam(@PathVariable String name) {
        return new ResponseEntity<>(new Response("Hello, " + name), HttpStatus.OK);        
    }

    @PostMapping("/bye")
    public ResponseEntity<Response> bye(@RequestBody Body reqBody) {
        return new ResponseEntity<>(new Response("Bye! " + reqBody.getMesg()), HttpStatus.OK);
    }
}
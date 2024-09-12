package com.anshul.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.anshul.demo.request.Body;
import com.anshul.demo.response.Response;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class DemoController {
    
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
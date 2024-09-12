package com.anshul.demo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {    
    private String body;

    public Response(String body) {
        this.body = body;
    }
}

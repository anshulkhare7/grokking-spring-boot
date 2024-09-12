package com.anshul.ecom.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {    
    private String message;

    public Response(String body) {
        this.message = body;
    }
}

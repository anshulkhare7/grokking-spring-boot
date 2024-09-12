package com.anshul.ecom.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.anshul.ecom.request.Body;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(GreetingsController.class)
public class GreetingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testHello() throws Exception {
        mockMvc.perform(get("/hello"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.message", is("Hello World!")));
    }

    @Test
    public void testHelloWithParam() throws Exception {
        String name = "John";
        mockMvc.perform(get("/hellowithparam/{name}", name))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.message", is("Hello, John")));
    }

    @Test
    public void testBye() throws Exception {
        Body reqBody = new Body();
        reqBody.setMesg("See you later!");

        mockMvc.perform(post("/bye")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(reqBody)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.message", is("Bye! See you later!")));
    }
}
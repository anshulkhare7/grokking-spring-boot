package com.anshul.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anshul.ecom.model.Employee;
import com.anshul.ecom.repository.EmployeeRepository;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoder encoder;

     @PostMapping("/register/user")
    public Employee registerUser(@RequestBody Employee employee) {
        employee.setPassword(encoder.encode(employee.getPassword()));
        employee.setUsername(employee.getUsername());
        employee.setRoles(employee.getRoles());
        return repository.save(employee);
    }
}

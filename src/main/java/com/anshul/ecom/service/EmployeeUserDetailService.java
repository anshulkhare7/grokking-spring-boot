package com.anshul.ecom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.anshul.ecom.model.Employee;
import com.anshul.ecom.repository.EmployeeRepository;

public class EmployeeUserDetailService implements UserDetailsService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
        Optional<Employee> employee = repository.findByUsername(username);
        if (employee.isPresent()) {
            var employeeObj = employee.get();
            return User.builder()
                .username(employeeObj.getUsername())
                .password(employeeObj.getPassword())
                .roles(getRoles(employeeObj))
                .build();
        }else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(Employee employee) {
        if (employee.getRoles() == null) {
            return new String[] {"USER"};
        }
        return employee.getRoles().split(",");
    }
    
}

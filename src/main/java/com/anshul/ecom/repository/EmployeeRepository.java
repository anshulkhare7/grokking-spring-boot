package com.anshul.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anshul.ecom.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
    Optional<Employee> findByUsername(String username);
}

package com.ivan.spring.spring_data_rest.dao;


import com.ivan.spring.spring_data_rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<User, Integer> {
}

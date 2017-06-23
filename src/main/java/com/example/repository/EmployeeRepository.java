package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	List<Employee> findByName(String name);
}

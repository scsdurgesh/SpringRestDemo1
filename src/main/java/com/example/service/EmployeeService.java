package com.example.service;

import java.util.List;

import com.example.entity.Employee;

public interface EmployeeService {

	void saveEmploye(Employee save);

	void deleteEmploye(Long empId);

	Employee getEmployeId(Long empId);

	List<Employee> getAllEmployee();

	List<Employee> getAllEmployeByName(String name);
}

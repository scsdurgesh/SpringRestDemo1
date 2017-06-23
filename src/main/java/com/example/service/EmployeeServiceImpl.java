package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import com.example.restController.EmployeeRestController;

@Service
@Transactional
@Component("employeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void saveEmploye(Employee employee) {
		// TODO Auto-generated method stub
		employeeRepository.save(employee);

	}

	@Override
	public void deleteEmploye(Long empId) {
		// TODO Auto-generated method stub
		employeeRepository.delete(empId);
	}

/*	@Override
	@Transactional(readOnly = true)
	public Employee getEmployeId(Long empId) {
		// TODO Auto-generated method stub
		Employee emp = employeeRepository.findOne(empId);
		emp.add(new Link("href", "next"));
		return emp;
	}*/

	@Override
	@Transactional(readOnly = true)
	public Employee getEmployeId(Long empId) {
		// TODO Auto-generated method stub
		Employee emp = employeeRepository.findOne(empId);
		try {
			emp.add(ControllerLinkBuilder.linkTo(EmployeeRestController.class,
					EmployeeRestController.class.getClass().getMethod("getEmployeeById"), 2).withRel("next"));
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> getAllEmployeByName(String name) {
		// TODO Auto-generated method stub
		return employeeRepository.findByName(name);
	}

}

package com.example.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.service.EmployeeService;

@RestController
@Scope("request")
@RequestMapping("/rest/employee")
public class EmployeeRestController {

	@Autowired
	@Qualifier("employeeServiceImpl")
	private EmployeeService employeeService;

	@RequestMapping(value = "/insert/{id}/{name}/{salary}", method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.CREATED)
	public void insertEmployee(@PathVariable("id") Long id,@PathVariable("name") String name, @PathVariable("salary") String salary) {

		Employee emp = new Employee();
		emp.setEmpId(id);
		emp.setName(name);
		emp.setSalary(Double.parseDouble(salary));
		employeeService.saveEmploye(emp);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT,consumes={"application/json","application/xml"})
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void updateEmployee(@RequestBody Employee employee) {
		employeeService.saveEmploye(employee);

	}
	
	@RequestMapping(value="/{empId}",method=RequestMethod.DELETE)
	@ResponseStatus(code=HttpStatus.OK)
	public void deleteEmployee(@PathVariable("empId") Long empId){
		employeeService.deleteEmploye(empId);
	}
	@RequestMapping(value="/{empId}", method=RequestMethod.GET, produces={"application/json","application/xml"})
	@ResponseStatus(code=HttpStatus.OK)
	public Employee getEmployeeById(@PathVariable("empId") Long empId){
		return employeeService.getEmployeId(empId);
	}

	@RequestMapping(method=RequestMethod.GET, produces={"application/json","application/xml"})
	@ResponseStatus(code=HttpStatus.OK)
	public List<Employee> getEmployees(){
		return employeeService.getAllEmployee();
	}
	
	
	@RequestMapping(value="/search/{name}", method=RequestMethod.GET, produces={"application/json","application/xml"})
	@ResponseStatus(code=HttpStatus.OK)
	public List<Employee> getEmployeeByName(@PathVariable("name") String name){
		return employeeService.getAllEmployeByName(name);
	}
}

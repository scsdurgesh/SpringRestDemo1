package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.example.repository"})
@EntityScan(basePackages={"com.example.entity"})
@ComponentScan(basePackages={"com.example.repository","com.example.restController","com.example.service"})
@EnableWebMvc
//@EnableEurekaClient
public class SpringRestDemoApplication {

	@Bean
	public CommandLineRunner runnerInsert(@Autowired EmployeeRepository employeeRepository) {
		CommandLineRunner runner = (args) -> {
			employeeRepository.save(new Employee(11L, "XYXZ", 000.0D));
			employeeRepository.save(new Employee(12L, "X", 000.0D));
			employeeRepository.findAll().forEach(System.out::println);
			
			System.out.println("---Find by Name----");
			employeeRepository.findByName("X").forEach(System.out::println);
			
		};
		
		return runner;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringRestDemoApplication.class, args);
	}
}

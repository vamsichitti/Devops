package com.employee;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;


@SpringBootApplication
public class EmployeeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}
	
	@Autowired
	EmployeeRepository empRepo;
	
	@PostConstruct
	public void initUsers()
	{
		   List<Employee> employees = Stream.of(
	                new Employee(2,"vamsi","pasupula","vamsi@gmail.com"),
	                new Employee(3,"monica","rangappa","monica@gmail.com"),
	                new Employee(3,"sabarish","Nunna","sabarish@gmail.com")
	                
	        ).collect(Collectors.toList());
	        empRepo.saveAll(employees);
	}

}

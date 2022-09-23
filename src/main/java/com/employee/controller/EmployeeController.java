package com.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.exceptions.ResourceAlreadyExists;
import com.employee.exceptions.ResourceNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	
	@Autowired
	EmployeeRepository empRepo;

	
	@GetMapping
	public List<Employee> getEmployeesList(){
		return empRepo.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
		Employee emp = empRepo.findById(id).orElseThrow(()
				->new ResourceNotFoundException("resource with id "+id+" not found"));
		
		return ResponseEntity.ok(emp);
	}
	
	@PostMapping("/create")
	public Employee createEmployee(@RequestBody Employee emp) {
		Optional<Employee> employee = empRepo.findById(emp.getId());
		if(employee.isPresent()) {
			throw new ResourceAlreadyExists("Resource already Exists");
		}
		else {
			return empRepo.save(emp);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee emp){
		Employee employee = empRepo.findById(id).orElseThrow(()
				->new ResourceNotFoundException("resource with id "+id+" not found"));
		
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		employee.setEmail(emp.getEmail());
		empRepo.save(employee);
		
		return new ResponseEntity<Employee>(employee,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){
		Employee employee = empRepo.findById(id).orElseThrow(()
				->new ResourceNotFoundException("resource with id "+id+" not found"));
		
		if(!(employee.getId()==0)) {
			empRepo.delete(employee);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
}

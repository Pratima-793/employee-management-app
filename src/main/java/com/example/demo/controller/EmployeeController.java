package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;

import com.example.demo.repository.EmployeeRepostitory;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
   private EmployeeRepostitory employeeRepository;
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
	    Employee emp = employeeRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
	    return ResponseEntity.ok(emp);
	}

	
	//create employee rest api
	
	@PostMapping("/employees")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee addEmployee(@RequestBody Employee employee){
		return employeeRepository.save(employee);
	}
   
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
	    Employee employee = employeeRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

	    employee.setFirstName(employeeDetails.getFirstName());
	    employee.setLastName(employeeDetails.getLastName());
	    employee.setEmailID(employeeDetails.getEmailID());

	    Employee updatedEmployee = employeeRepository.save(employee);
	    return ResponseEntity.ok(updatedEmployee);
	}
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable Long id) {
	    Employee employee = employeeRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

	    employeeRepository.delete(employee);

	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Employee deleted successfully");

	    return ResponseEntity.ok(response);
	}
	
}

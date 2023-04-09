package com.learning.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learning.employee.datamodel.EmployeeResponse;
import com.learning.employee.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee/{id}")
	public ResponseEntity<EmployeeResponse> getEmployeeResponseById(@PathVariable("id") int id) {
		return employeeService.getEmployeeById(id);
	}
}

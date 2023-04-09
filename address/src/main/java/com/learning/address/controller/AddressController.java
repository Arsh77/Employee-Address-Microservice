package com.learning.address.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learning.address.datamodel.AddressResponse;
import com.learning.address.service.AddressService;

@RestController
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/address/{id}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("id") int id) {
		return addressService.getAddressByEmployeeId(id);
	}

}

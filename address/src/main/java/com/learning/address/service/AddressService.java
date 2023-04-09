package com.learning.address.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learning.address.datamodel.AddressResponse;
import com.learning.address.entity.AddressEntity;
import com.learning.address.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(int employee_id){
		AddressEntity addressEntity = addressRepository.getAddressByEmployeeId(employee_id);
		AddressResponse addressResponse = modelMapper.map(addressEntity, AddressResponse.class);
		return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
		
	} 

}

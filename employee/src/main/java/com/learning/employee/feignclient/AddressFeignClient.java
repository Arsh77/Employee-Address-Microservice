package com.learning.employee.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.employee.datamodel.AddressResponse;

@FeignClient(name = "ADDRESS-SERVICE", path="/address-app/api")
public interface AddressFeignClient {

	@GetMapping("/address/{id}")
	public AddressResponse getAddressByEmployeeId(@PathVariable("id") int id);
}

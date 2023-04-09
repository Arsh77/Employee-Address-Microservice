package com.learning.employee.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.learning.employee.datamodel.AddressResponse;
import com.learning.employee.datamodel.EmployeeResponse;
import com.learning.employee.entity.EmployeeEntity;
import com.learning.employee.feignclient.AddressFeignClient;
import com.learning.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private AddressFeignClient addressFeignClient;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@Autowired
	private EurekaDiscoveryClient eurekaDiscoveryClient;
	
	@Value("${addressApp}") 
	private String addressServiceName;
	
	public ResponseEntity<EmployeeResponse> getEmployeeById(int id) {
		
		EmployeeEntity employeeEntity = employeeRepository.findEmployeeById(id);
		EmployeeResponse employeeResponse = modelMapper.map(employeeEntity, EmployeeResponse.class);
		AddressResponse addressResponse = addressFeignClient.getAddressByEmployeeId(id); 
				//getAddressByWebClient(id);//addressFeignClient.getAddressByEmployeeId(id);//getAddressByWebClient(id);//getAddressByRestTemplate(id);
		employeeResponse.setAddress(addressResponse);
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
		
	}
	
	public AddressResponse getAddressByRestTemplate(int id) {
		System.out.println("service name in employee>> "+addressServiceName);
		ServiceInstance serviceInstance = loadBalancerClient.choose("ADDRESS-SERVICE");
		String uri = serviceInstance.getUri().toString()+serviceInstance.getMetadata().get("serviceContextName"); 
		System.out.println("URI in employee "+uri);
		return restTemplate.getForObject(uri+"/address/{id}", AddressResponse.class, id);
	}
	
	public AddressResponse getAddressByWebClient(int id) {
		ServiceInstance serviceInstance = loadBalancerClient.choose("ADDRESS-SERVICE");
		String uri = serviceInstance.getUri().toString()+serviceInstance.getMetadata().get("serviceContextName"); 
		System.out.println("URI in employee "+uri);
		return webClient.get().uri(uri+"/address/"+id).retrieve().bodyToMono(AddressResponse.class).block();
	}
}

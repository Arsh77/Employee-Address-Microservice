package com.learning.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learning.address.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer>{
	
	@Query(value = "select a.id, a.street1, a.street2, a.city, a.state, a.pincode, a.employee_id from address a join employee e on a.employee_id = e.id where a.employee_id= :employee_id", nativeQuery=true)
	public AddressEntity getAddressByEmployeeId(@Param("employee_id") int employee_id);
}

package com.learning.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.employee.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>{	

	@Query(value = "select * from employee e where e.id= :id", nativeQuery = true)
	public EmployeeEntity findEmployeeById(int id);
}

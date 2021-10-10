package com.completewebapplication.repository;

import org.springframework.data.repository.CrudRepository;

import com.completewebapplication.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
   public Long countById(int id);
}

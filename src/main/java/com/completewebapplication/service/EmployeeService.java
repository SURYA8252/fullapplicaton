package com.completewebapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.completewebapplication.entity.Employee;
import com.completewebapplication.repository.EmployeeRepository;
@Service
public class EmployeeService {
	@Autowired
    private EmployeeRepository employeeRepository;
	public List<Employee> listAll()
	{
		return (List<Employee>) employeeRepository.findAll();
	}
	public void save(Employee employee)
	{
		employeeRepository.save(employee);
	}
	public Employee get(int id) throws Exception 
	{
		Optional<Employee> result = employeeRepository.findById(id);
		if(result.isPresent())
		{
			return result.get();
		}
		throw new Exception("Employee not found !!"+id);
	}
	public void delete(int id) throws Exception
	{
		Long count=employeeRepository.countById(id);
		if(count==null || count==0)
		{
			throw new Exception("Employee not found !!"+id);
		}
		employeeRepository.deleteById(id);
	}
}

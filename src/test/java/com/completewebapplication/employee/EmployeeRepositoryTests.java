package com.completewebapplication.employee;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.completewebapplication.entity.Employee;
import com.completewebapplication.repository.EmployeeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class EmployeeRepositoryTests {
    @Autowired
	private EmployeeRepository employeeRepository;
    @Test
    public void testAddNew()
    {
    	Employee employee=new Employee();
    	employee.setFirstName("Ram");
    	employee.setLastName("Kumar");
    	employee.setGender("Male");
    	employee.setPhone("1234567890");
    	employee.setEmail("Ram4@gmail.com");
    	employee.setAddress("Patna");
    	Employee saveEmployee = employeeRepository.save(employee);
    	Assertions.assertThat(saveEmployee).isNotNull();
    	Assertions.assertThat(saveEmployee.getId()).isGreaterThan(0);
    }
    @Test
    public void testListAll()
    {
    	Iterable<Employee> employees=employeeRepository.findAll();
    	Assertions.assertThat(employees).hasSizeGreaterThan(0);
    	for(Employee employee : employees)
    	{
    		System.out.println(employee);
    	}
    }
    @Test
    public void testEmployeeUpdate()
    {
    	int employeeId = 22;
    	Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
    	Employee employee = optionalEmployee.get();
    	employee.setAddress("Mumbai");
    	employeeRepository.save(employee);
    	Employee updatedEmployee = employeeRepository.findById(employeeId).get();
    	Assertions.assertThat(updatedEmployee.getAddress()).isEqualTo("Mumbai");
    }
    @Test
    public void testEmployeeGet()
    {
    	int employeeId = 22;
    	Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
    	Assertions.assertThat(optionalEmployee).isPresent();
    	System.out.println(optionalEmployee.get());
    }
    @Test
    public void testEmployeeDelete()
    {
    	int employeeId = 22;
    	employeeRepository.deleteById(employeeId);
    	Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
    	Assertions.assertThat(optionalEmployee).isNotPresent();
    }
}

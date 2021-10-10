package com.completewebapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.completewebapplication.entity.Employee;
import com.completewebapplication.service.EmployeeService;

@Controller
public class EmployeeController {
   @Autowired
   private EmployeeService employeeService;
   @GetMapping("/employee")
   public String showEmployeeList(Model model)
   {
	   List<Employee> listEmployees = employeeService.listAll();
	   model.addAttribute("listemployees",listEmployees);
	   return "employee";
   }
   @GetMapping("/employee/add")
   public String showNewEmployee(Model model)
   {
	   model.addAttribute("employee",new Employee());
	   model.addAttribute("pageTitle","Add New Employee");
	   return "employee-add";
   }
   @PostMapping("/employee/save")
   public String saveNewEmployee(Employee employee,RedirectAttributes redirectAttributes)
   {
	   employeeService.save(employee);
	   redirectAttributes.addFlashAttribute("message","The Employee has been saved Successfully....");
	   return "redirect:/employee";
   }
   @GetMapping("/employee/edit/{id}")
   public String showEditForm(@PathVariable("id") int id,Model model,RedirectAttributes redirectAttributes)
   {
	   try
	   {
		  Employee employee= employeeService.get(id);
		  model.addAttribute("employee",employee);
		  model.addAttribute("pageTitle","Edit Employee(ID : "+id+")");
		  return "employee-add";
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
		   redirectAttributes.addFlashAttribute("message",e.getMessage());
		   return "redirect:/employee";
	   }
   }
   @GetMapping("/employee/delete/{id}")
   public String showDeleteForm(@PathVariable("id") int id,RedirectAttributes redirectAttributes)
   {
	   try
	   {
		   employeeService.delete(id);
		   redirectAttributes.addFlashAttribute("message","The Employee ID "+id +" has been delete");
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
		   redirectAttributes.addFlashAttribute("message",e.getMessage());
	   }
	   return "redirect:/employee";
   }
}

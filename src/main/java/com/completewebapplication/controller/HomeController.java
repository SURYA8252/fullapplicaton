package com.completewebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.completewebapplication.entity.User;
import com.completewebapplication.repository.UserRepository;

@Controller
public class HomeController {
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/")
    public String index()
    {
		System.out.println("Index Page.....");
    	return "index";
    }
	@GetMapping("/register")
	public String showRegisterForm(Model model)
	{
		System.out.println("Register Page Here....");
		model.addAttribute("user",new User());
		return "register";
	}
	@PostMapping("/process")
	public String processRegistation(User user)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);
		return "redirect:/register?success";
	}
	@GetMapping("/welcome")
	public String welcome()
	{
		System.out.println("Welcome to our Application......");
		return "welcome";
	}
}

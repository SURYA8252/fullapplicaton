package com.completewebapplication.passwordencoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "nam2021";
		String encoderPassword = encoder.encode(rawPassword);
		System.out.println(encoderPassword);
	}

}

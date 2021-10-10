package com.completewebapplication;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.completewebapplication.entity.User;
import com.completewebapplication.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private TestEntityManager entityManager;
	@Test
	public void testCreateUser()
	{
		User user=new User();
		user.setFirstName("Shivani");
		user.setLastName("Saw");
		user.setEmail("shivani5@gmail.com");
		user.setPassword("12345");
		User savedUser=userRepository.save(user);
		User existUser=entityManager.find(User.class, savedUser.getId());
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	}
	@Test
	public void testFindUserByEmail()
	{
		String email = "surya@gmail.com";
		User user=userRepository.findByEmail(email);
		assertThat(user).isNotNull();
	}
}

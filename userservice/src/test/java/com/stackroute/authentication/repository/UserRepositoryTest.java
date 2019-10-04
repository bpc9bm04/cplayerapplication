package com.stackroute.authentication.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.authentication.domain.User;
import com.stackroute.authentication.repository.UserRepository;

/**
 * UserRepository test class
 * @author Tarun K Tiwari
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class UserRepositoryTest {

	@Mock
	private transient UserRepository userRepository;
	private User user;

	
	@Before
	public void setup() {
		user = new User("u101", "Tarun", "Kumar", "password", new Date());
	}
	
	@Test
	public void testRegisterUserSuccess() {
		userRepository.save(user);
		Optional<User> optionalUser = userRepository.findById(user.getUserId());		
		assertThat(optionalUser.equals(user));
	}
	public void testLoginUserSuccess() {
		userRepository.save(user);
		Optional<User> optionalUser = userRepository.findById(user.getUserId());		
		assertThat(optionalUser.equals(user));
	}
}

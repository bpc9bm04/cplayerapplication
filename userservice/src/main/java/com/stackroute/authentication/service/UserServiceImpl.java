package com.stackroute.authentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.authentication.domain.User;
import com.stackroute.authentication.exception.UserExistException;
import com.stackroute.authentication.exception.UserNotFoundException;
import com.stackroute.authentication.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	/* (non-Javadoc)
	 * @see com.vb.mca.moviecruiser.auth.service.UserService#saveUser(com.vb.mca.moviecruiser.auth.domain.User)
	 */
	@Override
	public boolean saveUser(User user) throws UserExistException {

		Optional<User> optionalUser = userRepository.findById(user.getUserId());
		if (optionalUser.isPresent()) {
			throw new UserExistException("User with same id already exists!");
		}
		userRepository.save(user);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.vb.mca.moviecruiser.auth.service.UserService#findByUserIdAndPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {

		User user = userRepository.findByUserIdAndPassword(userId, password);
		if (user == null) {
			throw new UserNotFoundException("Invalid user id or password!");
		}

		return user;
	}

}

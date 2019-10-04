package com.stackroute.authentication.service;

import com.stackroute.authentication.domain.User;
import com.stackroute.authentication.exception.UserExistException;
import com.stackroute.authentication.exception.UserNotFoundException;

public interface UserService {

	boolean saveUser(User user) throws UserExistException;
	User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
}

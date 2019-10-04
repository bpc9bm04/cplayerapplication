package com.stackroute.authentication.service;

import java.util.Map;

import com.stackroute.authentication.domain.User;

public interface SecurityTokenGenerator {

	Map<String, String> generateToken(User user);
}

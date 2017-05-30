package com.rest.service;

import java.util.List;

import com.rest.dto.User;

public interface UserService {

	List<User> getUsers();

	User saveUser(User user);

	List<User> getUser(Integer id);

	static boolean isNull(Integer value) {

		return value == null ? true : false;
	}
}

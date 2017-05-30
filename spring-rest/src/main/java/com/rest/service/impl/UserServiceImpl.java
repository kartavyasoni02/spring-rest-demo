package com.rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rest.dto.User;
import com.rest.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	List<User> listOfUsers = new ArrayList<>();

	private Integer defaultId = 111;

	public UserServiceImpl() {
		listOfUsers.add(new User(111, "Kartavya", "Soni"));
		listOfUsers.add(new User(112, "Rakesh", "Soni"));
	}

	@Override
	public List<User> getUsers() {
		return listOfUsers;
	}

	@Override
	public List<User> getUser(Integer id) {
		return listOfUsers.stream().filter(u -> u.getId() == id).collect(Collectors.<User> toList());
	}

	@Override
	public User saveUser(User user) {
		if (UserService.isNull(user.getId())) {
			user.setId(getNextId());
		}
		listOfUsers.add(user);
		return user;
	}

	private Integer getNextId() {
		return listOfUsers.isEmpty() ? defaultId : listOfUsers.get(listOfUsers.size() - 1).getId() + 1;
	}

}

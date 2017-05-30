package com.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rest.dto.User;
import com.rest.exception.UserNotFoundException;
import com.rest.service.UserService;

/**
 * 
 * @author kartavya.soni
 * @since 15-06-2017
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * This method is to get all user details
	 * 
	 * @return list of User
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsers() throws Exception {
		return userService.getUsers();
	}

	/**
	 * This method is to get User details based on Id
	 * 
	 * @param id
	 * 
	 * @return list of User complete user details
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUserById(@PathVariable(value = "id", required = true) Integer id) throws Exception {

		List<User> users = userService.getUser(id);
		if (users.isEmpty()) {
			throw new UserNotFoundException("error.user.notfound");
		}
		return users;
	}

	/**
	 * This method is used to save a user
	 * 
	 * 
	 * @return User with id
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@Valid @RequestBody User user) throws Exception {
		return userService.saveUser(user);
	}
}
package com.wedlum.styleprofile.service;

import java.util.List;

import com.wedlum.styleprofile.domain.User;

public interface StyleProfileService {

	User findUserById(Long id);
	User findUserByEmail(String email);
	List<User> findAllUsers();

	void saveUser(User user);

}
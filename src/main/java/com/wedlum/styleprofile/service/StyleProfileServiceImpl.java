package com.wedlum.styleprofile.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wedlum.styleprofile.dao.UserDao;
import com.wedlum.styleprofile.domain.User;

@Service(value = "userService")
@Transactional
public class StyleProfileServiceImpl implements StyleProfileService {

	@Inject
	private UserDao userDao;

	@Override
	public User findUserById(Long id) {
		return userDao.get(id);
	}

	@Override
	public User findUserByEmail(String email) {
		return userDao.getByEmail(email);
	}

	@Override
	public List<User> findAllUsers() {
		return userDao.getAll();
	}

	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

}

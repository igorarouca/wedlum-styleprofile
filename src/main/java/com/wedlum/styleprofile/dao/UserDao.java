package com.wedlum.styleprofile.dao;

import com.wedlum.styleprofile.domain.User;

public interface UserDao extends GenericDao<User> {

	User getByEmail(String email);

}

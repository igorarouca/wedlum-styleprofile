package com.wedlum.styleprofile.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.wedlum.styleprofile.dao.UserDao;
import com.wedlum.styleprofile.dao.jpa.exception.EntityNotFoundException;
import com.wedlum.styleprofile.domain.User;

@Repository("userDao")
public class UserDaoUsingJpa extends JpaDao<User> implements UserDao {

	public UserDaoUsingJpa() {
		super(User.class);
	}

	@Override
	public User getByEmail(String email) {
		List<User> result = null;

		Query query = getEntityManager().createQuery("from User as p where p.email = :email");
		query.setParameter("email", email);

		result = getResultList(query);

		if (result == null || result.size() <= 0) {
			throw new EntityNotFoundException("No user with email: " + email + " was found");
		}

		return result.get(0);

	}


}

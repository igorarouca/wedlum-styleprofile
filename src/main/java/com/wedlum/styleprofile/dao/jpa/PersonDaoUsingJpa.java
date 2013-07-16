package com.wedlum.styleprofile.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.wedlum.styleprofile.dao.PersonDao;
import com.wedlum.styleprofile.dao.jpa.exception.EntityNotFoundException;
import com.wedlum.styleprofile.domain.Person;

@Repository("personDao")
public class PersonDaoUsingJpa extends JpaDao<Person> implements PersonDao {

	public PersonDaoUsingJpa() {
		super(Person.class);
	}

	@Override
	public List<Person> getByFullName(String firstName, String LastName) {
		List<Person> result = null;

		Query query = getEntityManager().createQuery("from Person as p where p.firstName = :firstName and p.lastName = :lastName");
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", firstName);

		result = getResultList(query);

		if (result == null || result.size() <= 0) {
			throw new EntityNotFoundException("No one named " + firstName + " " + LastName + " was found");
		}

		return result;
	}

}

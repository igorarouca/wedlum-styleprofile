package com.wedlum.styleprofile.dao;

import java.util.List;

import com.wedlum.styleprofile.domain.Person;

public interface PersonDao extends GenericDao<Person> {

	List<Person> getByFullName(String firstName, String LastName);

}

package com.wedlum.styleprofile.dao;

import java.util.List;

import com.wedlum.styleprofile.domain.DomainObject;

public interface GenericDao<T extends DomainObject> {

	T get(Long id);

	List<T> getAll();

	void save(T object);

	void delete(T object);

}

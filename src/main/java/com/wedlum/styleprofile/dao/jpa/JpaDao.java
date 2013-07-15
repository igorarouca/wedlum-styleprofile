package com.wedlum.styleprofile.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.wedlum.styleprofile.dao.GenericDao;
import com.wedlum.styleprofile.domain.DomainObject;

public abstract class JpaDao<T extends DomainObject> implements GenericDao<T> {

	private Class<T> type;
	private EntityManager entityManager;

	protected JpaDao(Class<T> type) {
		this.type = type;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public T get(Long id) {
		if (id == null) return null;

		return entityManager.find(type, id);
	}

	@Override
	@Transactional
	public List<T> getAll() {
		return getResultList(entityManager.createQuery("select o from " + type.getName() + "o"));
	}

	@Override
	public void save(T object) {
		entityManager.persist(object);
	}

	@Override
	public void delete(T object) {
		entityManager.remove(object);
	}

	@SuppressWarnings("unchecked")
	protected List<T> getResultList(Query query) {
		return query.getResultList();
	}

}

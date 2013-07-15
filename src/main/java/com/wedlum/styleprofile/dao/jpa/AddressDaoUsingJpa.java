package com.wedlum.styleprofile.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.wedlum.styleprofile.dao.AddressDao;
import com.wedlum.styleprofile.dao.jpa.exception.EntityNotFoundException;
import com.wedlum.styleprofile.domain.Address;

@Repository("addressDao")
public class AddressDaoUsingJpa extends JpaDao<Address> implements AddressDao {

	public AddressDaoUsingJpa() {
		super(Address.class);
	}

	@Override
	public List<Address> getByZipCode(String zipCode) {
		List<Address> result = null;

		Query query = getEntityManager().createQuery("from Person as p where p.username = :username");
		query.setParameter("zipCode", zipCode);

		result = getResultList(query);

		if (result == null || result.size() <= 0) {
			throw new EntityNotFoundException("No addresses for zip code: " + zipCode);
		}

		return result;

	}

}

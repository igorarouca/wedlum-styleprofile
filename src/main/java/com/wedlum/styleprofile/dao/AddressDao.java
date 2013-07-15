package com.wedlum.styleprofile.dao;

import java.util.List;

import com.wedlum.styleprofile.domain.Address;

public interface AddressDao extends GenericDao<Address> {

	List<Address> getByZipCode(String zipCode);

}

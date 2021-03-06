package com.wedlum.styleprofile.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Address implements DomainObject {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer version;

	private String zipCode;

	protected Address() {}

	public Address(String zipCode) {
		this.zipCode = zipCode;
	}

	@Id
	@GeneratedValue
	public final Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Version
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", zipCode=" + zipCode + "]";
	}

}

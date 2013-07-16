package com.wedlum.styleprofile.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
public class Person implements Serializable, DomainObject {

	public static enum Gender { FEMALE, MALE };

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer version;

	private String firstName;
	private String lastName;
	private Gender gender;
	private DateTime birthdate;
	private Address address;

	protected Person() {}

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person(String firstName, String lastName, Gender gender, DateTime birthdate, Address address) {
		this(firstName, lastName);
		this.gender = gender;
		this.birthdate = birthdate;
		this.address = address;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getBirthdate() {
		return birthdate;
	}

	@Transient
	public String getBirthDateString() {
		String birthDateString = "";
		if (birthdate != null)
			birthDateString = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd").print(birthdate);

		return birthDateString;
	}

	public void setBirthdate(DateTime birthdate) {
		this.birthdate = birthdate;
	}

	@Column(columnDefinition = "enum('MALE','FEMALE')")
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "address_id")
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	// Disclaimer: The ID is set only after the entity is persisted
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	// Disclaimer: The ID is set only after the entity is persisted
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;

		if (getClass() != obj.getClass())
			return false;

		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}

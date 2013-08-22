package com.wedlum.styleprofile.domain;

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
public class Person implements DomainObject {

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

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}

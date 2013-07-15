package com.wedlum.styleprofile.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class User implements Serializable, DomainObject {

	public static enum Role { ADMIN, TEST_TAKER, VENDOR };

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer version;

	private String email;
	private String password;

	@Column(columnDefinition = "enum('MALE','FEMALE')")
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToOne
	@JoinColumn(name = "person_id")
	private Person person;

	User() {}

	public User(String email, String password, Role role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public User(String email, String password, Role role, Person person) {
		this(email, password, role);
		this.person = person;
	}

	@Id
	@GeneratedValue
	public Long getId() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Basic(fetch = FetchType.LAZY)
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;

		if (getClass() != obj.getClass())
			return false;

		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + "]";
	}

}

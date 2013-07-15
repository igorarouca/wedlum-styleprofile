package com.wedlum.styleprofile.domain.photo;

import static junit.framework.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

import com.wedlum.styleprofile.domain.Address;
import com.wedlum.styleprofile.domain.Person;
import com.wedlum.styleprofile.domain.Person.Gender;

public class PersonTest {

	private Person subject;

	@Test
	public void testInstantiation() {
		subject = new Person(
			"Igor",
			"Arouca",
			Gender.MALE,
			DateTime.parse("1982-03-01", DateTimeFormat.forPattern("yyyy-MM-dd")),
			new Address("61801")
		);

		assertEquals("Igor", subject.getFirstName());
		assertEquals("Arouca", subject.getLastName());
		assertEquals(Gender.MALE, subject.getGender());
		assertEquals("1982-03-01", subject.getBirthDateString());
		assertEquals("61801", subject.getAddress().getZipCode());
	}

}

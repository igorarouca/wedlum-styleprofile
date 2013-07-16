package com.wedlum.styleprofile.dao;

import static junit.framework.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.wedlum.styleprofile.domain.Address;
import com.wedlum.styleprofile.domain.Person;
import com.wedlum.styleprofile.domain.Person.Gender;
import com.wedlum.styleprofile.domain.User;
import com.wedlum.styleprofile.domain.User.Role;
import com.wedlum.styleprofile.service.StyleProfileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/spring/root-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional()
public class StyleProfileServiceTest {

	@Autowired
	private StyleProfileService subject;

	@Test
	public void testSaveUser() {
		Person person =
			new Person(
				"Igor",
				"Arouca",
				Gender.MALE,
				DateTime.parse("1982-03-01", DateTimeFormat.forPattern("yyyy-MM-dd")),
				new Address("61801")
		);
		
		User user = new User("igor.arouca@acm.org", "0123456789", Role.TEST_TAKER, person);

		// User is persisted
		subject.saveUser(user);

		user = subject.findUserByEmail("igor.arouca@acm.org");
		assertEquals("igor.arouca@acm.org", user.getEmail());
		assertEquals("0123456789", user.getPassword());

		person = user.getPerson();
		assertEquals("Igor", person.getFirstName());
		assertEquals("Arouca", person.getLastName());
		assertEquals(Gender.MALE, person.getGender());
		assertEquals("1982-03-01", person.getBirthDateString());

		Address address = person.getAddress();
		assertEquals("61801", address.getZipCode());
	}
	
}

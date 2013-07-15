package com.wedlum.styleprofile.domain.photo;

import static junit.framework.Assert.*;

import org.junit.Test;

import com.wedlum.styleprofile.domain.User;
import com.wedlum.styleprofile.domain.User.Role;

public class UserTest {

	private User subject;

	@Test
	public void testInstantiation() {
		subject = new User("igor.arouca@acm.org", "0123456789", Role.TEST_TAKER);
		assertEquals("igor.arouca@acm.org", subject.getEmail());
		assertEquals("0123456789", subject.getPassword());
	}

}

package com.wedlum.styleprofile.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wedlum.styleprofile.business.model.User;

@Controller
@RequestMapping("api")
public class UserController {

	private static final User MOCK = new User("Igor Arouca", "906 W Clark St, Apt 4");

	@RequestMapping("user/mock")
	@ResponseBody
	public User mock() {
		return MOCK;
	}

	@RequestMapping("user/list")
	@ResponseBody
	public List<User> list() {
		return Arrays.asList(mock());
	}

}

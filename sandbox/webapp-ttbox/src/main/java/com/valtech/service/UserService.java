package com.valtech.service;

import java.util.ArrayList;
import java.util.List;

import com.valtech.vo.UserVO;

public class UserService {

	public static final String LASTNAME_PREFIX = "lastname";
	private static List<UserVO> list = new ArrayList<UserVO>();
	static {
		UserVO user;

		for (int i = 0; i < 10; i++) {
			user = new UserVO();
			user.setFirstname("firstname" + i);
			user.setLastname(LASTNAME_PREFIX + i);
			user.setAge(i);
			list.add(user);
		}
	}

	public List<UserVO> retrieveAllUser() {
		return list;
	}

	public UserVO getUserByLastName(String lastName) {
		Integer i = Integer.valueOf(lastName.replaceAll(LASTNAME_PREFIX
				+ "([0-9]*)$", "$1"));
		return list.get(i);
	}
}

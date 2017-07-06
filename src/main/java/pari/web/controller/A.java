package pari.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pari.business.dao.UserDao;
import pari.business.model.User;

@RestController
public class A {

	@Autowired
	private UserDao users;

	@GetMapping("/x/usr")
	public List<User> all() {
		return users.findAll();
	}

	@GetMapping("/x")
	public String h() {
		return "hello";
	}
}
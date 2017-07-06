package pari.web.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pari.business.dao.UserDao;
import pari.business.model.User;

@RestController
public class A {

	@Autowired
	private UserDao dao;

	@GetMapping("/users")
	@RolesAllowed(User.ROLE_ADMIN)
	public List<User> a() {
		return dao.findAll();
	}
}
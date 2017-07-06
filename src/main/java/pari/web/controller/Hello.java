package pari.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pari.auth.AuthService;

@CrossOrigin
@RestController
public class Hello {

	@Autowired
	private AuthService auth;

	@GetMapping("/hello/{id}")
	public String hi(@PathVariable("id") Object id) {
		return String.format("%s -> Hello, %s", auth.currentUser(), id);
	}
}
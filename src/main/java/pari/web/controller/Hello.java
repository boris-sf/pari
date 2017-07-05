package pari.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class Hello {

	@GetMapping("/game/{id}")
	public String hi(@PathVariable("id") Object id) {
		return String.format("Hello, %s", id);
	}
}
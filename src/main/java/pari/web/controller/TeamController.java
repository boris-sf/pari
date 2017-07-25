package pari.web.controller;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pari.business.model.Team;
import pari.business.service.TeamService;

@CrossOrigin
@RestController
@RequestMapping("/team")
public class TeamController {

	@Autowired
	private TeamService teams;

	@GetMapping
	public List<Team> lookup(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
		return teams.lookup(name);
	}

	@GetMapping("/all")
	public List<Team> teams() {
		return teams.findAll();
	}

	@PutMapping("/{id}")
	public Team update(@RequestBody Team team) {
		return teams.update(team);
	}

	@PostMapping
	public Team create(@RequestBody Team team) {
		return teams.create(team.getName(), team.getLogo());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") long id) {
		teams.delete(id);
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ResponseEntity handleBadInput(ConstraintViolationException ex) {
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

}
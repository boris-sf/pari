package pari.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pari.business.model.Team;
import pari.business.service.TeamService;

import java.util.List;

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
}
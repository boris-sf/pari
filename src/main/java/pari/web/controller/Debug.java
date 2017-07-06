package pari.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pari.business.dao.GameDao;
import pari.business.dao.GuessDao;
import pari.business.dao.TeamDao;
import pari.business.dao.UserDao;
import pari.business.model.Game;
import pari.business.model.Guess;
import pari.business.model.Team;
import pari.business.model.User;

@RestController
@RequestMapping("/debug")
public class Debug {

	@Autowired
	private UserDao users;
	@Autowired
	private TeamDao teams;
	@Autowired
	private GameDao games;
	@Autowired
	private GuessDao guesses;

	@GetMapping("/users")
	public List<User> users() {
		return users.findAll();
	}

	@GetMapping("/teams")
	public List<Team> teams() {
		return teams.findAll();
	}

	@GetMapping("/games")
	public List<Game> games() {
		return games.findAll();
	}

	@GetMapping("/guesses")
	public List<Guess> guesses() {
		return guesses.findAll();
	}
}
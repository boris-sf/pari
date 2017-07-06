package pari.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pari.business.model.Game;
import pari.business.service.GameService;

@CrossOrigin
@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired
	private GameService games;

	@GetMapping
	public List<Game> upcoming(@RequestParam(name = "upcoming", required = false, defaultValue = "true") boolean up) {
		return games.lookup(up);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") long id) {
		games.delete(id);
	}
}
package pari.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pari.business.model.Guess;
import pari.business.model.Score;
import pari.business.service.GuessService;

@CrossOrigin
@RestController
@RequestMapping("/guess")
public class GuessController {

	@Autowired
	private GuessService guesses;

	@GetMapping("/{id}")
	public Guess lookup(@PathVariable("id") long id) {
		return guesses.lookup(id);
	}

	@PostMapping("/{gameId}")
	public Guess create(@PathVariable("gameId") long gameId, @RequestBody Score score) {
		return guesses.create(gameId, score);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") long id) {
		guesses.delete(id);
	}
}
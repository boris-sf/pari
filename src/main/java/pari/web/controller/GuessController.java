package pari.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("/{gameId}")
	public Guess lookup(@PathVariable("gameId") long gameId) {
		return guesses.lookup(gameId);
	}

	@PutMapping("/{gameId}")
	public Guess update(@PathVariable("gameId") long gameId, @RequestBody Score score) {
		return guesses.update(gameId, score);
	}

	@DeleteMapping("/{gameId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("gameId") long gameId) {
		guesses.delete(gameId);
	}
}
package pari.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pari.business.model.Guess;
import pari.business.service.GuessService;
import pari.web.dto.NewGuessDto;

@CrossOrigin
@RestController
@RequestMapping("/guess")
public class GuessController {

	@Autowired
	private GuessService guesses;

	@GetMapping
	public List<Guess> active(@RequestParam(name = "active", required = false, defaultValue = "true") boolean active) {
		return guesses.lookup(active);
	}

	@PostMapping
	public Guess create(@RequestBody NewGuessDto guess) {
		return guesses.create(guess.getGameId(), guess.getScore());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") long id) {
		guesses.delete(id);
	}
}
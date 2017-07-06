package pari.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pari.business.model.Game;
import pari.business.model.Score;
import pari.business.service.GameService;
import pari.web.dto.GameDto;
import pari.web.dto.NewGameDto;

@CrossOrigin
@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired
	private GameService games;

	@GetMapping
	public List<GameDto> upcoming(
			@RequestParam(name = "upcoming", required = false, defaultValue = "true") boolean upcoming) {
		final List<GameDto> result = new ArrayList<>();
		for (Game game : games.lookup(upcoming)) {
			result.add(dto(game));
		}
		return result;
	}

	@PostMapping
	public GameDto create(@RequestBody NewGameDto game) {
		return dto(games.create(game.getTeamA(), game.getTeamB(), game.getStartDate()));
	}

	@PutMapping("/{id}")
	public GameDto update(@PathVariable("id") long id, @RequestBody Score score) {
		return dto(games.update(id, score));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") long id) {
		games.delete(id);
	}

	private static GameDto dto(Game game) {
		final GameDto dto = new GameDto();
		dto.setStartDate(game.getStartDate());
		dto.setTeamA(game.getTeamA().id());
		dto.setTeamB(game.getTeamB().id());
		dto.setScore(game.getScore());
		dto.setId(game.id());
		return dto;
	}
}
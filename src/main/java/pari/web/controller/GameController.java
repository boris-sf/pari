package pari.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pari.business.dao.GameDao;
import pari.business.model.Game;

@RestController
@CrossOrigin("*")
@RequestMapping("/game")
public class GameController {

	@Autowired
	private GameDao games;

	@GetMapping("/all")
	public List<Object> readAll() {
		final List<Object> result = new ArrayList<>();
		for (Game game : games.findAll()) {
			final Map<String, Object> obj = new HashMap<>();
			obj.put("startDate", game.getStartDate());
			obj.put("id", game.id());
			result.add(obj);
		}
		return result;
	}
}
package pari.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import pari.business.dao.GameDao;
import pari.business.model.Game;
import pari.business.service.TeamService;

@Component
public class Startup {

	@Autowired
	private GameDao games;
	@Autowired
	private TeamService teams;

	@EventListener(ContextRefreshedEvent.class)
	public void startUp() {
		games.save(game(new Date()));
		teams.create("sparta", "http://sparta");
		teams.create("arsenal", "http://arsenal");
		teams.create("barselona", "http://barselona");
	}

	private static Game game(Date startDate) {
		final Game game = new Game();
		game.setStartDate(startDate);
		return game;
	}
}
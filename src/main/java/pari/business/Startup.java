package pari.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import pari.business.dao.GameDao;
import pari.business.model.Game;

@Component
public class Startup {

	@Autowired
	private GameDao games;

	@EventListener(ContextRefreshedEvent.class)
	public void startUp() {
		games.save(game(new Date()));
	}

	private static Game game(Date startDate) {
		final Game game = new Game();
		game.setStartDate(startDate);
		return game;
	}
}
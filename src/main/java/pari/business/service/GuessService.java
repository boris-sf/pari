package pari.business.service;

import static java.lang.String.format;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pari.business.dao.GameDao;
import pari.business.dao.GuessDao;
import pari.business.model.Game;
import pari.business.model.Guess;
import pari.business.model.Score;

@Service
public class GuessService {

	@Autowired
	private UserService users;
	@Autowired
	private GuessDao guesses;
	@Autowired
	private GameDao games;

	public Guess lookup(long gameId) {
		return guesses.lookup(gameId, users.currentUser().id());
	}

	public Guess create(long gameId, int scoreA, int scoreB) {
		final Guess guess = new Guess();
		guess.setScore(new Score(scoreA, scoreB));
		guess.setUser(users.currentUser());
		guess.setGame(game(gameId));
		return guesses.save(guess);
	}

	public Guess update(long gameId, Score score) {
		final Guess guess = lookup(gameId);
		if (guess == null) {
			throw new IllegalArgumentException(format("Guess for game=%s not found for current user", gameId));
		}
		if (guess.getGame().overdue()) {
			throw new IllegalStateException(format("Game is alredy started"));
		}
		guess.setScore(score);
		return guesses.save(guess);
	}

	public void delete(long gameId) {
		final Guess guess = lookup(gameId);
		if (guess != null) {
			guesses.delete(guess);
		}
	}

	private Game game(long id) {
		final Game game = games.findOne(id);
		if (game == null) {
			throw new IllegalArgumentException(format("Game with id=%s not found", id));
		}
		return game;
	}
}
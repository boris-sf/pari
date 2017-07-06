package pari.business.service;

import static java.lang.String.format;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pari.auth.AuthService;
import pari.business.dao.GameDao;
import pari.business.dao.GuessDao;
import pari.business.model.Game;
import pari.business.model.Guess;
import pari.business.model.Score;

@Service
public class GuessService {

	@Autowired
	private AuthService auth;
	@Autowired
	private GuessDao guesses;
	@Autowired
	private GameDao games;

	public Guess create(long game, int scoreA, int scoreB) {
		final Guess guess = new Guess();
		guess.setScore(new Score(scoreA, scoreB));
		guess.setUser(auth.currentUser());
		guess.setGame(game(game));
		return guesses.save(guess);
	}

	public Guess update(long game, Score score) {
		final Guess guess = lookup(game);
		if (guess == null) {
			throw new IllegalArgumentException(format("Guess for game=%s not found for current user", game));
		}
		if (guess.getGame().overdue()) {
			throw new IllegalStateException(format("Game is alredy started"));
		}
		guess.setScore(score);
		return guesses.save(guess);
	}

	public Guess lookup(long game) {
		return guesses.lookup(game, auth.currentUser().id());
	}

	public void delete(long game) {
		final Guess guess = lookup(game);
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
package pari.business.service;

import static java.lang.String.format;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pari.business.dao.GameDao;
import pari.business.dao.GuessDao;
import pari.business.model.Game;
import pari.business.model.Guess;
import pari.business.model.Score;
import pari.business.model.User;

@Service
public class GuessService {

	@Autowired
	private UserService users;
	@Autowired
	private GuessDao guesses;
	@Autowired
	private GameDao games;

	public List<Guess> lookup(boolean active) {
		final long userId = currentUser().id();
		return active ? guesses.lookup(userId, new Date()) : guesses.findAll(userId);
	}

	public Guess create(long gameId, Score score) {
		Guess guess = guesses.lookup(currentUser().id(), gameId);
		return update(guess == null ? new Guess(currentUser(), game(gameId)) : guess, score);
	}

	private Guess update(Guess guess, Score score) {
		if (guess.getGame().overdue()) {
			throw new IllegalStateException(format("Game is alredy started"));
		}
		guess.setScore(score);
		return guesses.save(guess);
	}

	public void delete(long id) {
		final Guess guess = guesses.findOne(id);
		if (guess != null) {
			if (guess.getUser().id() != currentUser().id()) {
				throw new IllegalArgumentException("Cannot delete other user's guess");
			}
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

	private User currentUser() {
		return users.currentUser();
	}
}
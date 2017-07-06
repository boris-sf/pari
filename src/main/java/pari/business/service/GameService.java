package pari.business.service;

import static java.lang.String.format;
import static pari.business.model.User.ROLE_ADMIN;

import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pari.business.dao.GameDao;
import pari.business.dao.TeamDao;
import pari.business.model.Game;
import pari.business.model.Score;
import pari.business.model.Team;

@Service
public class GameService {

	@Autowired
	private GameDao games;
	@Autowired
	private TeamDao teams;

	public List<Game> lookup(boolean upcoming) {
		return upcoming ? games.upcoming(new Date()) : games.all();
	}

	@RolesAllowed(ROLE_ADMIN)
	public Game create(long teamA, long teamB, Date startDate) {
		final Game game = new Game();
		game.setStartDate(startDate);
		game.setTeamA(team(teamA));
		game.setTeamB(team(teamB));
		return games.save(game);
	}

	@RolesAllowed(ROLE_ADMIN)
	public Game update(long id, Score score) {
		final Game game = games.findOne(id);
		if (game == null) {
			throw new IllegalArgumentException(format("Game with id=%s not found", id));
		}
		if (game.getScore() != null && game.getScore().complete()) {
			throw new IllegalStateException(format("Game score is already completed"));
		}
		if (game.getStartDate().getTime() > System.currentTimeMillis()) {
			throw new IllegalStateException(format("Game is not started yet"));
		}
		game.setScore(score);
		return games.save(game);
	}

	@RolesAllowed(ROLE_ADMIN)
	public void delete(long id) {
		final Game game = games.findOne(id);
		if (game != null) {
			games.delete(game);
		}
	}

	private Team team(long id) {
		final Team team = teams.findOne(id);
		if (team == null) {
			throw new IllegalArgumentException(format("Team with id=%s not found", id));
		}
		return team;
	}
}
package pari.business.service;

import static java.lang.String.format;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pari.business.dao.GameDao;
import pari.business.dao.TeamDao;
import pari.business.model.Game;
import pari.business.model.Team;

@Service
public class GameService {

	@Autowired
	private GameDao games;
	@Autowired
	private TeamDao teams;

	public Game create(long teamA, long teamB, Date startDate) {
		final Game game = new Game();
		game.setStartDate(startDate);
		game.setTeamA(team(teamA));
		game.setTeamB(team(teamB));
		return games.save(game);
	}

	public List<Game> upcoming() {
		return games.upcoming(new Date());
	}

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
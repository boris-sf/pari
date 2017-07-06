package pari.web.dto;

import pari.business.model.Score;

public class NewGuessDto {

	private long gameId;
	private Score score;

	public long getGameId() {
		return gameId;
	}

	public Score getScore() {
		return score;
	}
}
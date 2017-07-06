package pari.web.dto;

import pari.business.model.Score;

public class GameDto extends NewGameDto {

	private long id;
	private Score score;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}
}
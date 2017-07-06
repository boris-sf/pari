package pari.web.dto;

import java.util.Date;

import pari.business.model.Score;

public class GameDto {

	private long id;
	private long teamA;
	private long teamB;
	private Score score;
	private Date startDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTeamA() {
		return teamA;
	}

	public void setTeamA(long teamA) {
		this.teamA = teamA;
	}

	public long getTeamB() {
		return teamB;
	}

	public void setTeamB(long teamB) {
		this.teamB = teamB;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
package pari.web.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NewGameDto {

	@JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "s")
	private Date startDate;
	private long teamA;
	private long teamB;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
}
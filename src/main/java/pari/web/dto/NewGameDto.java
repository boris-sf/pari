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

	public long getTeamA() {
		return teamA;
	}

	public long getTeamB() {
		return teamB;
	}
}
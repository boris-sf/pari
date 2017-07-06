package pari.business.model;

public class Score {

	private Integer scoreA;
	private Integer scoreB;

	Score() {
	}

	public Score(int scoreA, int scoreB) {
		this.scoreA = scoreA;
		this.scoreB = scoreB;
	}

	public boolean complete() {
		return scoreA != null && scoreB != null;
	}

	public Integer getScoreA() {
		return scoreA;
	}

	public void setScoreA(Integer scoreA) {
		this.scoreA = scoreA;
	}

	public Integer getScoreB() {
		return scoreB;
	}

	public void setScoreB(Integer scoreB) {
		this.scoreB = scoreB;
	}
}
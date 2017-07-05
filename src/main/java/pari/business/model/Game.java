package pari.business.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Game extends BaseEntity {

	private Date startDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
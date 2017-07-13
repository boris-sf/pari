package pari.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Team extends BaseEntity {

	@Column(unique = true)
	private String name;

	private String logo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
}
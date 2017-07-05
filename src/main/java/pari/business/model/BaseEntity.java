package pari.business.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	
	@Id
	@GeneratedValue
	private long id;

	public long id() {
		return id;
	}
}
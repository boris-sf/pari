package pari.business.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue
	private long id;

	@JsonProperty
	public long id() {
		return id;
	}
}
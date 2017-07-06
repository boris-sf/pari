package pari.business.model;

import static java.lang.Integer.toHexString;
import static java.lang.String.format;

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

	@Override
	public String toString() {
		return format("%s[%s]: id=%s", getClass().getSimpleName(), toHexString(hashCode()), id);
	}
}
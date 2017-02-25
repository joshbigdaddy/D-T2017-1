package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Attribute extends DomainEntity{

public Attribute() {
		super();
	}
private String name;
private String value;

@NotBlank
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@NotBlank
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}

}

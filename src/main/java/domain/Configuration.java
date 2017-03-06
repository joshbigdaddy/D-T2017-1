package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;



@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity{

	public Configuration(){
		super();
	
	}
	private Double fee;
	
	@NotNull
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	

//RELATIONSHIPS
	
	
	

}

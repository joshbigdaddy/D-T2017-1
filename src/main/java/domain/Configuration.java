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
	private Integer vat;
	
	@NotNull
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Integer getVat() {
		return vat;
	}

	public void setVat(Integer vat) {
		this.vat = vat;
	}

	//RELATIONSHIPS
	
	
	

}

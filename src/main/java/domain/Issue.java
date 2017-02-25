package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Issue extends DomainEntity{

	public Issue() {
		super();
	}
	
	private String creditCardNumber;
	private String details;
	private String tenantInfo;
	private Double amount;
	private Integer vatNumber;
	private Date moment;
	
	@NotBlank
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	@NotBlank
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@NotNull
	public String getTenantInfo() {
		return tenantInfo;
	}
	public void setTenantInfo(String tenantInfo) {
		this.tenantInfo = tenantInfo;
	}
	@NotNull
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@NotNull
	public Integer getVatNumber() {
		return vatNumber;
	}
	public void setVatNumber(Integer vatNumber) {
		this.vatNumber = vatNumber;
	}
	
	@NotNull
	@Past
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	
}

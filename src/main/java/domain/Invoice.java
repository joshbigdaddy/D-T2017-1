package domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Invoice extends DomainEntity{

	public Invoice() {
		super();
	}
	
	private String creditCardNumber;
	private Double amount;
	private Integer vatNumber;
	private Date moment;
	private Request request;
	
	@NotBlank
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
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
	private Tenant tenant;

	@ManyToOne
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
}

package domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Request extends DomainEntity{

	public enum RequestType {
		PENDING,ACCEPTED,DENIED
	}


	public Request() {
		super();
	}
	
	private RequestType state;
	private String creditCardNumber;
	private boolean smoker;
	private Date checkinDate;
	private Date checkoutDate;

	@Enumerated(EnumType.STRING)
	public RequestType getState() {
		return state;
	}
	public void setState(RequestType state) {
		this.state = state;
	}
	
	@NotBlank
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	@NotNull
	public boolean isSmoker() {
		return smoker;
	}
	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}
	@NotNull
	public Date getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}
	@NotNull
	public Date getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	
	private Property property;
	
	@ManyToOne(optional =false, 
			fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	

}

	

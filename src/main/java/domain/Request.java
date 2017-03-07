package domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Request extends DomainEntity{

	public enum RequestType {
		PENDING,ACCEPTED,DENIED;

        @Override
        public String toString() {
            return super.toString();
        }
    }


	public Request() {
		super();
	}
	
	private RequestType state;
	private boolean smoker;
	private Date checkinDate;
	private Date checkoutDate;
	private Tenant tenant;
	private Invoice invoice;

	@Enumerated(EnumType.STRING)
	public RequestType getState() {
		return state;
	}
	public void setState(RequestType state) {
		this.state = state;
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
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}

	@ManyToOne()
	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	@OneToOne(mappedBy = "request",cascade = CascadeType.ALL)
	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
}

	

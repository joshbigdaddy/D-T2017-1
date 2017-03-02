package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Property extends DomainEntity{

	public Property() {
		super();
		
	}
	private String name;
	private String description;
	private String address;
	private Integer rate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	
	private Collection<Attribute> attributes;
	private Collection<Request> requests;
	private Collection<Audit> audits;
	
	
	@OneToMany()
	public Collection<Audit> getAudits() {
		return audits;
	}
	public void setAudits(Collection<Audit> audits) {
		this.audits = audits;
	}
	@OneToMany()
	public Collection<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(Collection<Attribute> attributes) {
		this.attributes = attributes;
	}
	@OneToMany()
	public Collection<Request> getRequests() {
		return requests;
	}
	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}
	//Propiedades derivadas
	public Integer getAcceptedRequests() {
		Integer total=0;
		for(Request r:requests){
			if(r.getState().equalsIgnoreCase("ACCEPTED"))
				total++;
		}
		
		return total;
	}
	public void setAcceptedRequests(Integer ratio) {
	}
	public Integer getDeniedRequests() {
		Integer total=0;
		for(Request r:requests){
			if(r.getState().equalsIgnoreCase("DENIED"))
				total++;
		}
		
		return total;
	}
	public void setDeniedRequests(Integer ratio) {
	}
	public Integer getPendingRequests() {
		Integer total=0;
		for(Request r:requests){
			if(r.getState().equalsIgnoreCase("PENDING"))
				total++;
		}
		
		return total;
	}
	public void setPendingRequests(Integer ratio) {
	}
}

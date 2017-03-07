package domain;
import java.util.Collection;

import javax.persistence.*;


@Entity
	@Access(AccessType.PROPERTY)
	public class Tenant  extends SocialUser{


	//RELATIONSHIPS
	
	private Collection<Request> requests;	
	@OneToMany(mappedBy = "tenant")
	public Collection<Request> getRequests(){
	return requests;	
	}
	public void setRequests(Collection<Request> requests){
		this.requests=requests;
	}
	private Collection<Invoice> invoices;
	@OneToMany(mappedBy = "tenant")
	public Collection<Invoice> getInvoices(){
	return invoices;
	}
	public void setInvoices(Collection<Invoice> invoices){
		this.invoices=invoices;
	}
	
	private Finder finder;	
	@OneToOne(mappedBy = "tenant")
	public Finder getFinder(){
	return finder;	
	}
	public void setFinder(Finder finder){
		this.finder=finder;
	}
}
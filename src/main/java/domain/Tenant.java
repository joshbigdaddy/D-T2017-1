package domain;
import java.util.Collection;

import javax.persistence.Access;
	import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



	@Entity
	@Access(AccessType.PROPERTY)
	public class Tenant  extends SocialUser{

		public Tenant(){
			super();
		
		}

	//RELATIONSHIPS
	private Collection<Invoice> issues;	
	@OneToMany()
	public Collection<Invoice> getIssues(){
	return issues;	
	}
	public void setIssues(Collection<Invoice> issues){
		this.issues=issues;
	}
	
	private Collection<Request> requests;	
	@OneToMany()
	public Collection<Request> getRequests(){
	return requests;	
	}
	public void setRequests(Collection<Request> requests){
		this.requests=requests;
	}
	
	private Finder finder;	
	@OneToOne()
	public Finder getFinder(){
	return finder;	
	}
	public void setRequests(Finder finder){
		this.finder=finder;
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
		public Integer getDeniedRequests() {
			Integer total=0;
			for(Request r:requests){
				if(r.getState().equalsIgnoreCase("DENIED"))
					total++;
			}
			
			return total;
		}
		public Integer getPendingRequests() {
			Integer total=0;
			for(Request r:requests){
				if(r.getState().equalsIgnoreCase("PENDING"))
					total++;
			}
			
			return total;
		}
		public Double getRatioRequests() {
			Double total = (double) (getAcceptedRequests()/getRequests().size());
			total=Math.floor(total * 10) / 10;

			return total;
		}
	
}
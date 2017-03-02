package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Lessor extends SocialUser {

	public Lessor() {
		super();

	}

	// RELATIONSHIPS
	private Collection<Property> properties;

	@OneToMany()
	public Collection<Property> getProperties() {
		return properties;
	}

	public void setProperties(Collection<Property> properties) {
		this.properties = properties;
	}

	// Propiedades derivadas
	public Integer getRequests() {
		Integer total = 0;
		for (Property p : properties) {
			total += p.getRequests().size();
		}

		return total;
	}
	public void setRequests(Integer ratio) {
	}
	public Integer getAcceptedRequests() {
		Integer total = 0;
		for (Property p : properties) {
			Collection<Request> requests = p.getRequests();
			for (Request r : requests) {
				if (r.getState().equalsIgnoreCase("ACCEPTED"))
					total++;
			}
		}

		return total;
	}
	public void setAcceptedRequests(Integer ratio) {
	}
	public Integer getDeniedRequests() {
		Integer total = 0;
		for (Property p : properties) {
			Collection<Request> requests = p.getRequests();
			for (Request r : requests) {
				if (r.getState().equalsIgnoreCase("DENIED"))
					total++;
			}
		}

		return total;
	}
	public void setDeniedRequests(Integer ratio) {
	}
	public Integer getPendingRequests() {
		Integer total = 0;
		for (Property p : properties) {
			Collection<Request> requests = p.getRequests();
			for (Request r : requests) {
				if (r.getState().equalsIgnoreCase("PENDING"))
					total++;
			}
		}

		return total;
	}
	public void setPendingRequests(Integer ratio) {
	}
	public Double getRatioRequests() {
		Double total = (double) (getAcceptedRequests()/getRequests());
		total=Math.floor(total * 10) / 10;

		return total;
	}
	public void setRatioRequests(Double ratio) {
	}
	
	
	

}
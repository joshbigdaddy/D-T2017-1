package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Auditor extends Actor {

	public Auditor() {
		super();

	}

	private String company;

	@NotBlank
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	// RELATIONSHIPS
	private Collection<Audit> audits;

	@OneToMany()
	public Collection<Audit> getAudits() {
		return audits;
	}

	public void setAudits(Collection<Audit> audits) {
		this.audits = audits;
	}

}

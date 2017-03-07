package domain;

import java.util.Collection;

import javax.persistence.*;

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

	@OneToMany(mappedBy = "auditor")
	public Collection<Audit> getAudits() {
		return audits;
	}

	public void setAudits(Collection<Audit> audits) {
		this.audits = audits;
	}

    @Override
    public String toString() {
        return super.toString()+"Auditor{" +
                "company='" + company + '\'' +
                ", audits=" + audits +
                '}';
    }
}

package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
public class Property extends DomainEntity{

	public Property() {
		super();
		requests=new ArrayList<Request>();
		audits=new ArrayList<Audit>();
		attributeValues=new ArrayList<AttributeValue>();
	}
	private String name;
	private String description;
	private String address;
	private Integer rate;
	private Lessor lessor;
	
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
	
	private List<AttributeValue> attributeValues;

	private Collection<Request> requests;
	private Collection<Audit> audits;
	
	
	@OneToMany(mappedBy= "property", cascade = CascadeType.REMOVE)
	public Collection<Audit> getAudits() {
		return audits;
	}
	public void setAudits(Collection<Audit> audits) {
		this.audits = audits;
	}

    @OneToMany(mappedBy = "property", cascade = CascadeType.MERGE)
    public List<AttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(List<AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }

    @OneToMany(mappedBy= "property", cascade = CascadeType.ALL)
	public Collection<Request> getRequests() {
		return requests;
	}
	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}

	@ManyToOne
	public Lessor getLessor() {
		return lessor;
	}

	public void setLessor(Lessor lessor) {
		this.lessor = lessor;
	}

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
				"id='" + getId() + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", rate=" + rate +
                ", lessor=" + lessor +
                ", attributeValues=" + attributeValues +
                ", requests=" + requests +
                ", audits=" + audits +
                '}';
    }
}

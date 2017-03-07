package domain;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Attribute extends DomainEntity{

    public Attribute() {
            super();
        }
    private String name;
    private Collection<AttributeValue> attributeValues;

    @NotBlank
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL)
    public Collection<AttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(Collection<AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }
}

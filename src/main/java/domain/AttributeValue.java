package domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class AttributeValue extends DomainEntity {

    private String value;
    private Attribute attribute;
    private Property property;

    @NotNull
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}

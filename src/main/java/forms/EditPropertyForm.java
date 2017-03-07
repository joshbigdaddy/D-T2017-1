package forms;


import domain.AttributeValue;
import domain.Property;

import java.util.List;

public class EditPropertyForm  {

    private Property property;
    private List<AttributeValue> attributesValue;

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public List<AttributeValue> getAttributesValue() {
        return attributesValue;
    }

    public void setAttributesValue(List<AttributeValue> attributesValue) {
        this.attributesValue = attributesValue;
    }
}

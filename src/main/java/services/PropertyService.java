package services;

import java.util.Collection;

import domain.AttributeValue;
import domain.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PropertyRepository;
import domain.Property;

@Service
@Transactional
public class PropertyService {

	// Managed repositories
	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
    private TenantService tenantService;

	@Autowired
    private AttributeValueService attributeValueService;

	@Autowired
    private RequestService requestService;

	// Constructor
	public PropertyService() {
		super();
	}

	// CRUD Methods

	public Collection<Property> findAll() {
		Collection<Property> result;
		result = propertyRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Property findOne(int propertyId) {
		Assert.isTrue(propertyId != 0);
		Property result;
		result = propertyRepository.findOne(propertyId);
		Assert.notNull(result);
		return result;
	}

	public void save(Property property) {
		Assert.notNull(property);
		propertyRepository.save(property);
	}

	public void delete(Property property) {
		Assert.notNull(property);
		Assert.isTrue(property.getId() != 0);
		Assert.isTrue(propertyRepository.exists(property.getId()));
		for(AttributeValue e: property.getAttributeValues()){
		    e.setProperty(null);
		    attributeValueService.save(e);
        }
        for(Request e: property.getRequests()){
            e.setProperty(null);
            requestService.save(e);
        }
		property.setRequests(null);
		property.setAttributeValues(null);
		property = propertyRepository.save(property);
		propertyRepository.delete(property);
	}
}

package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import domain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.PropertyRepository;

@Service
@Transactional
public class PropertyService {

	// Managed repositories
	@Autowired
	private PropertyRepository propertyRepository;



	@Autowired
    private ActorService actorService;

	@Autowired
    private AttributeValueService attributeValueService;

	@Autowired
    private RequestService requestService;

	@Autowired
    private Validator validator;
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

	public Property save(Property property) {
		Assert.notNull(property);
		List<AttributeValue> attributeValues = new ArrayList<>();
        for(AttributeValue e: property.getAttributeValues()) {
            e.setProperty(property);
            attributeValues.add(e);
        }
        property.setAttributeValues(attributeValues);
        property = propertyRepository.save(property);
        return property;

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

    public Property reconstruct(Property property, List<AttributeValue> attributesValue, BindingResult bindingResult,Boolean edit) {
        Property result;

        if (!edit){
            result = property;
            result.setAttributeValues(attributesValue);
        }else {
            result = propertyRepository.findOne(property.getId());
            result.setAttributeValues(attributesValue);
            result.setAddress(property.getAddress());
            result.setName(property.getName());
            result.setDescription(property.getDescription());
        }
        result.setLessor((Lessor) actorService.findActorByPrincipal());
		validator.validate(result, bindingResult);

        return result;
    }
    
    
    
	public Double avgAuditsPerProperty(){
		return propertyRepository.avgAuditsPerProperty();
	}
	
	public Double minAuditsPerProperty(){
		return propertyRepository.minAuditsPerProperty();
	}
	
	public Double maxAuditsPerProperty(){
		return propertyRepository.maxAuditsPerProperty();
	}
	public Double avgRequestsPerPropertyWithAudits(){
		return propertyRepository.avgRequestsPerPropertyWithAudits();
	}
	
	public Double avgRequestsPerPropertyWithoutAudits(){
		return propertyRepository.avgRequestsPerPropertyWithoutAudits();
	}
	
	public Collection<Property> propertiesOrderByAudits(int lessorId){
		return propertyRepository.propertiesOrderByAudits(lessorId);
	}
	
	public Collection<Property> propertiesOrderByRequests(int lessorId){
		return propertyRepository.propertiesOrderByRequests(lessorId);
	}
	
	public Collection<Object[]> propertiesOrderByAcceptedRequests(int lessorId){
		return propertyRepository.propertiesOrderByAcceptedRequests(lessorId);
	}
	
    public Collection<Object[]> propertiesOrderByDeniedRequests(int lessorId){
    	return propertyRepository.propertiesOrderByDeniedRequests(lessorId);
    }
	
	public Collection<Object[]> propertiesOrderByPendingRequests(int lessorId){
		return propertyRepository.propertiesOrderByPendingRequests(lessorId);
	}
}

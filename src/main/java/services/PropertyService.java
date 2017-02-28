package services;

import java.util.Collection;

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
		propertyRepository.delete(property);
	}
}

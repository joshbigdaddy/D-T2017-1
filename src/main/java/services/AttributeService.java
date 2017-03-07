package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AttributeRepository;
import domain.Attribute;

@Service
@Transactional
public class AttributeService {

	// Managed repositories
	@Autowired
	private AttributeRepository attributeRepository;

	// Constructor
	public AttributeService() {
		super();
	}

	// CRUD Methods

	public Collection<Attribute> findAll() {
		Collection<Attribute> result;
		result = attributeRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Attribute findOne(int attributeId) {
		Assert.isTrue(attributeId != 0);
		Attribute result;
		result = attributeRepository.findOne(attributeId);
		Assert.notNull(result);
		return result;
	}

	public void save(Attribute attribute) {
		Assert.notNull(attribute);
		attributeRepository.save(attribute);
	}

	public void delete(Attribute attribute) {
		Assert.notNull(attribute);
		Assert.isTrue(attribute.getId() != 0);
		Assert.isTrue(attributeRepository.exists(attribute.getId()));
		attributeRepository.delete(attribute);
	}
	
	public Collection<Attribute> getAllAttributesByNumberOfTimesInProperty(){
		return attributeRepository.getAllAttributesByNumberOfTimesInProperty();
	}
}

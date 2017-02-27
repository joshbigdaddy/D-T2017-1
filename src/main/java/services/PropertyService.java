package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PropertyRepository;

@Service
@Transactional
public class PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;

	public PropertyService() {
		super();
	}
}

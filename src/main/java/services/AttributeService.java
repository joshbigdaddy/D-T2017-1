package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AttributeRepository;

@Service
@Transactional
public class AttributeService {

	@Autowired
	private AttributeRepository attributeRepository;
	
	
	public AttributeService(){
		super();
	}
}

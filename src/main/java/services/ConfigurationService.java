package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConfigurationRepository;
import domain.Configuration;

@Service
@Transactional
public class ConfigurationService {

	// Managed repositories
	@Autowired
	private ConfigurationRepository configurationRepository;

	// Constructor
	public ConfigurationService() {
		super();
	}

	// CRUD Methods

	public Collection<Configuration> findAll() {
		Collection<Configuration> result;
		result = configurationRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Configuration findOne(int configurationId) {
		Assert.isTrue(configurationId != 0);
		Configuration result;
		result = configurationRepository.findOne(configurationId);
		Assert.notNull(result);
		return result;
	}

	public void save(Configuration configuration) {
		Assert.notNull(configuration);
		configurationRepository.save(configuration);
	}

	public void delete(Configuration configuration) {
		Assert.notNull(configuration);
		Assert.isTrue(configuration.getId() != 0);
		Assert.isTrue(configurationRepository.exists(configuration.getId()));
		configurationRepository.delete(configuration);
	}

	public Configuration getFirstConfiguration(){
		List<Configuration> configurationList = new ArrayList<>(findAll());

		return configurationList.get(0);
	}
}

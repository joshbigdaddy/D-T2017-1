package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	// Manager repositories
	@Autowired
	private AdministratorRepository administratorRepository;

	// Constructor
	public AdministratorService() {
		super();
	}

	// CRUD Methods

	public Collection<Administrator> findAll() {
		Collection<Administrator> result;
		result = administratorRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Administrator findOne(int administratorId) {
		Assert.isTrue(administratorId != 0);
		Administrator result;

		result = administratorRepository.findOne(administratorId);
		Assert.notNull(result);
		return result;
	}

	public void save(Administrator administrator) {
		Assert.notNull(administrator);
		administratorRepository.save(administrator);
	}

	public void delete(Administrator administrator) {
		Assert.notNull(administrator);
		Assert.isTrue(administrator.getId() != 0);
		Assert.isTrue(administratorRepository.exists(administrator.getId()));
		administratorRepository.delete(administrator);
	}

}

package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;

@Service
@Transactional
public class FinderService {

	// Managed repositories
	@Autowired
	private FinderRepository finderRepository;

	// Constructor
	public FinderService() {
		super();
	}

	// CRUD Methods
	public Collection<Finder> findAll() {
		Collection<Finder> result;
		result = finderRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Finder findOne(int finderId) {
		Assert.isTrue(finderId != 0);
		Finder result;
		result = finderRepository.findOne(finderId);
		Assert.notNull(result);
		return result;
	}

	public void save(Finder finder) {
		Assert.notNull(finder);
		finderRepository.save(finder);
	}

	public void delete(Finder finder) {
		Assert.notNull(finder);
		Assert.isTrue(finder.getId() != 0);
		Assert.isTrue(finderRepository.exists(finder.getId()));
		finderRepository.delete(finder);
	}

}

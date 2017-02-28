package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LessorRepository;
import domain.Lessor;

@Service
@Transactional
public class LessorService {

	// Managed repositories
	@Autowired
	private LessorRepository lessorRepository;

	// Constructor
	public LessorService() {

		super();
	}

	// CRUD Methods
	public Collection<Lessor> findAll() {
		Collection<Lessor> result;
		result = lessorRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Lessor findOne(int lessorId) {
		Assert.isTrue(lessorId != 0);
		Lessor result;
		result = lessorRepository.findOne(lessorId);
		Assert.notNull(result);
		return result;
	}

	public void save(Lessor lessor) {
		Assert.notNull(lessor);
		lessorRepository.save(lessor);
	}

	public void delete(Lessor lessor) {
		Assert.notNull(lessor);
		Assert.isTrue(lessor.getId() != 0);
		Assert.isTrue(lessorRepository.exists(lessor.getId()));
		lessorRepository.delete(lessor);
	}

}

package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuditorRepository;
import domain.Auditor;

@Service
@Transactional
public class AuditorService {

	// Managed repositories
	@Autowired
	private AuditorRepository auditorRepository;

	// Constructor
	public AuditorService() {
		super();
	}

	// CRUD Methods

	public Collection<Auditor> findAll() {
		Collection<Auditor> result;
		result = auditorRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Auditor findOne(int auditorId) {
		Assert.isTrue(auditorId != 0);
		Auditor result;
		result = auditorRepository.findOne(auditorId);
		Assert.notNull(result);
		return result;
	}

	public void save(Auditor auditor) {
		Assert.notNull(auditor);
		auditorRepository.save(auditor);
	}

	public void delete(Auditor auditor) {
		Assert.notNull(auditor);
		Assert.isTrue(auditor.getId() != 0);
		Assert.isTrue(auditorRepository.exists(auditor.getId()));
		auditorRepository.delete(auditor);
	}
}

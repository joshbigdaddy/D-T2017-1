package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuditRepository;
import domain.Audit;

@Service
@Transactional
public class AuditService {

	// Managed repositories
	@Autowired
	private AuditRepository auditRepository;

	// Constructor
	public AuditService() {
		super();
	}

	// CRUD Methods

	public Collection<Audit> findAll() {
		Collection<Audit> result;
		result = auditRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Audit findOne(int auditId) {
		Assert.isTrue(auditId != 0);
		Audit result;
		result = auditRepository.findOne(auditId);
		Assert.notNull(result);
		return result;
	}

	public void save(Audit audit) {
		Assert.notNull(audit);
		auditRepository.delete(audit);
	}

	public void delete(Audit audit) {
		Assert.notNull(audit);
		Assert.isTrue(audit.getId() != 0);
		Assert.isTrue(auditRepository.exists(audit.getId()));
		auditRepository.delete(audit);
	}

}

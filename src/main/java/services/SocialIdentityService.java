package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SocialIdentityRepository;
import domain.SocialIdentity;

@Service
@Transactional
public class SocialIdentityService {

	// Manager repositories
	@Autowired
	private SocialIdentityRepository socialIdentityRepository;

	// Constructor
	public SocialIdentityService() {
		super();
	}

	// CRUD Methods

	public Collection<SocialIdentity> findAll() {
		Collection<SocialIdentity> result;
		result = socialIdentityRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public SocialIdentity findOne(int socialIdentityId) {
		Assert.isTrue(socialIdentityId != 0);
		SocialIdentity result;

		result = socialIdentityRepository.findOne(socialIdentityId);
		Assert.notNull(result);
		return result;
	}

	public void save(SocialIdentity socialIdentity) {
		Assert.notNull(socialIdentity);
		socialIdentityRepository.save(socialIdentity);
	}

	public void delete(SocialIdentity socialIdentity) {
		Assert.notNull(socialIdentity);
		Assert.isTrue(socialIdentity.getId() != 0);
		Assert.isTrue(socialIdentityRepository.exists(socialIdentity.getId()));
		socialIdentityRepository.delete(socialIdentity);
	}

}

package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TenantRepository;
import domain.Lessor;
import domain.Property;
import domain.Request;
import domain.Tenant;
import domain.Request.RequestType;

@Service
@Transactional
public class TenantService {

	// Managed repositories
	@Autowired
	private TenantRepository tenantRepository;

	// Constructor
	public TenantService() {
		super();
	}

	// CRUD Methods

	public Collection<Tenant> findAll() {
		Collection<Tenant> result;
		result = tenantRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Tenant findOne(int tenantId) {
		Assert.isTrue(tenantId != 0);
		Tenant result;
		result = tenantRepository.findOne(tenantId);
		Assert.notNull(result);
		return result;
	}

	public void save(Tenant tenant) {
		Assert.notNull(tenant);
		tenantRepository.save(tenant);
	}

	public void delete(Tenant tenant) {
		Assert.notNull(tenant);
		Assert.isTrue(tenant.getId() != 0);
		Assert.isTrue(tenantRepository.exists(tenant.getId()));
		tenantRepository.delete(tenant);
	}

	Double avgDeniedRequestsPerTenant() {
		List<Tenant> tenants = tenantRepository.findAll();
		int denied = 0;
		int total = 0;
		for (Tenant t : tenants) {
			for (Request r : t.getRequests()) {
				if (r.getState() == RequestType.DENIED)
					denied++;
				total++;
			}
		}
		return (double) (denied / total);
	}
}

package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TenantRepository;
import security.LoginService;
import security.UserAccount;
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

	@Autowired
	private RequestService requestService;

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

	public Double avgDeniedRequestsPerTenant() {
		Collection<Tenant> tenants = findAll();
		double denied = 0.;
		double total = 0.;
		for (Tenant t : tenants) {
			for (Request r : t.getRequests()) {
				if (r.getState() == RequestType.DENIED)
					denied += 1.;
				total += 1.;
			}
		}
		if (total == 0.) {
			return 0.;
		} else {
			return (denied / total);
		}

	}

	public Collection<Request> getAllRequestsAcceptedByTenant(int id) {
		List<Request> result = new ArrayList<>();
		Tenant p = tenantRepository.findOne(id);
		Assert.notNull(p);

		for (Request r : p.getRequests()) {
			if (r.getState() == RequestType.ACCEPTED) {
				result.add(r);
			}
		}

		Assert.notNull(result);
		return result;
	}

	public void chargeTenant(Request request) {
		Double fee = requestService.getAmount(request);
		Double actualFee = request.getTenant().getCreditCard().getFee();
		request.getTenant().getCreditCard().setFee(actualFee + fee);
	}

	public Tenant findTenantByPrincipal() {
		Tenant result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = tenantRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Collection<Object[]> maxRequestsApprovedTenant() {
		return tenantRepository.maxRequestsApprovedTenant();
	}

	public Collection<Object[]> maxRequestsDeniedTenant() {
		return tenantRepository.maxRequestsDeniedTenant();
	}

	public Collection<Object[]> maxRequestsPendingTenant() {
		return tenantRepository.maxRequestsPendingTenant();
	}

	public Integer minInvoicesPerTenant() {
		return tenantRepository.minInvoicesPerTenant();
	}

	public Integer maxInvoicesPerTenant() {
		return tenantRepository.maxInvoicesPerTenant();
	}

	public Double avgInvoicesPerTenant() {
		return tenantRepository.avgInvoicesPerTenant();
	}

	public Map<Tenant, Double> tenantRatioMaxVsMin() {

		Collection<Tenant> tenants = findAll();
		Map<Tenant, Double> map = new HashMap<Tenant, Double>();
		for (Tenant l : tenants) {
			double resultado = 0.;
			if (l.getRequests().size() != 0) {
				resultado = getAllRequestsAcceptedByTenant(l.getId()).size()
						/ l.getRequests().size();
			}
			map.put(l, resultado);
		}
		return map;
	}

	public Collection<Tenant> minRatio() {
		Map<Tenant, Double> map = tenantRatioMaxVsMin();
		Collection<Tenant> tenants = map.keySet();

		Collection<Tenant> tenantsRes = new ArrayList<Tenant>();
		double i = 123456.;
		for (Tenant l : tenants) {
			if (i == 123456.) {
				i = map.get(l);
				tenantsRes.add(l);
			} else {
				double e = map.get(l);
				if (e < i) {
					tenantsRes.clear();
					tenantsRes.add(l);
					i = e;
				} else if (e == i) {
					tenantsRes.add(l);
				}
			}
		}
		return tenantsRes;
	}

	public Collection<Tenant> maxRatio() {
		Map<Tenant, Double> map = tenantRatioMaxVsMin();
		Collection<Tenant> tenants = map.keySet();

		Collection<Tenant> tenantsRes = new ArrayList<Tenant>();
		double i = 123456.;
		for (Tenant l : tenants) {
			if (i == 123456.) {
				i = map.get(l);
				tenantsRes.add(l);
			} else {
				double e = map.get(l);
				if (e > i) {
					tenantsRes.clear();
					tenantsRes.add(l);
					i = e;
				} else if (e == i) {
					tenantsRes.add(l);
				}
			}
		}
		return tenantsRes;
	}

}

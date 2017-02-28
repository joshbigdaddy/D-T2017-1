package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant,Integer>{
	@Query("Select l from Tenant l where l.acceptedRequests=(select max(l1.acceptedRequests) from Tenant l1)")
	Collection<Tenant> maxRequestsApprovedTenant();
	@Query("Select l from Tenant l where l.deniedRequests=(select max(l1.deniedRequests) from Tenant l1)")
	Collection<Tenant> maxRequestsDeniedTenant();
	@Query("Select l from Tenant l where l.pendingRequests=(select max(l1.pendingRequests) from Tenant l1)")
	Collection<Tenant> maxRequestsPendingTenant();
	@Query("Select l from Tenant l where l.ratioRequests=(select max(l1.ratioRequests) from Tenant l1)")
	Collection<Tenant> maxRatio();
	@Query("Select l from Tenant l where l.ratioRequests=(select min(l1.ratioRequests) from Tenant l1)")
	Collection<Tenant> minRatio();
}

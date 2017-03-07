package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant,Integer>{
	@Query("select t,(select count(r) from Tenant t2 join t2.requests r where r.state = 'ACCEPTED' and t.id=t2.id) as mr from Tenant t order by mr DESC")
	Collection<Object[]> maxRequestsApprovedTenant();
	@Query("select t,(select count(r) from Tenant t2 join t2.requests r where r.state = 'DENIED' and t.id=t2.id) as mr from Tenant t order by mr DESC")
	Collection<Object[]> maxRequestsDeniedTenant();
	@Query("select t,(select count(r) from Tenant t2 join t2.requests r where r.state = 'PENDING' and t.id=t2.id) as mr from Tenant t order by mr DESC")
	Collection<Object[]> maxRequestsPendingTenant();

	@Query("Select min(l.invoices.size) from Tenant l")
	Integer minInvoicesPerTenant();
	@Query("Select max(l.invoices.size) from Tenant l")
	Integer maxInvoicesPerTenant();
	@Query("Select avg(l.invoices.size) from Tenant l")
	Double avgInvoicesPerTenant();
	@Query("select a from Tenant a where a.userAccount.id = ?1")
	Tenant findByUserAccountId(int userAccountId);
}

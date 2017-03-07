package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property,Integer>{
	
	@Query("select avg(p.audits.size) from Property p")
	Double avgAuditsPerProperty();
	@Query("select min(p.audits.size) from Property p")
	Double minAuditsPerProperty();
	@Query("select max(p.audits.size) from Property p")
	Double maxAuditsPerProperty();
	
	@Query("select avg(p.requests.size) from Property p where p.audits.size>0")
	Double avgRequestsPerPropertyWithAudits();
	
	@Query("select avg(p.requests.size) from Property p where p.audits.size<1")
	Double avgRequestsPerPropertyWithoutAudits();
	
	@Query("select p from Lessor l join l.properties p where l.id=?1 order by p.audits.size")
	Collection<Property> propertiesOrderByAudits(int lessorId);
	
	@Query("select p from Lessor l join l.properties p where l.id=?1 order by p.requests.size")
	Collection<Property> propertiesOrderByRequests(int lessorId);
	
	@Query("select p,(select count(r) from Property p2 join p2.requests r where r.state = 'ACCEPTED' and p2.lessor.id=?1 and p.id=p2.id) as mr from Property p order by mr DESC")
	Collection<Property> propertiesOrderByAcceptedRequests(int lessorId);
	
	@Query("select p,(select count(r) from Property p2 join p2.requests r where r.state = 'DENIED' and p2.lessor.id=?1 and p.id=p2.id) as mr from Property p order by mr DESC")
	Collection<Property> propertiesOrderByDeniedRequests(int lessorId);
	
	@Query("select p,(select count(r) from Property p2 join p2.requests r where r.state = 'PENDING' and p2.lessor.id=?1 and p.id=p2.id) as mr from Property p order by mr DESC")
	Collection<Property> propertiesOrderByPendingRequests(int lessorId);
	
}
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
	
//	@Query("select p from Lessor l join l.properties p where l.id=?1 order by p.acceptedRequests.size")
//	Collection<Property> propertiesOrderByAcceptedRequests(int lessorId);
//
//	@Query("select p from Lessor l join l.properties p where l.id=?1 order by p.deniedRequests.size")
//	Collection<Property> propertiesOrderByDeniedRequests(int lessorId);
//
//	@Query("select p from Lessor l join l.properties p where l.id=?1 order by p.pendingRequests.size")
//	Collection<Property> propertiesOrderByPendingRequests(int lessorId);
	
}
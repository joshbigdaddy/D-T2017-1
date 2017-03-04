package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Audit;
import domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer>{
//	@Query("select avg(l.deniedRequests) from Lessor l")
//	Double avgDeniedRequestsPerLessor();
//	@Query("select avg(t.deniedRequests) from Tenant t")
//	Double avgDeniedRequestsPerTenant();
	
}
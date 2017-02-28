package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Audit;
import domain.Lessor;

@Repository
public interface LessorRepository extends JpaRepository<Lessor,Integer>{
	@Query("Select l from Lessor l where l.acceptedRequests=(select max(l1.acceptedRequests) from Lessor l1)")
	Collection<Lessor> maxRequestsApprovedLessor();
	@Query("Select l from Lessor l where l.deniedRequests=(select max(l1.deniedRequests) from Lessor l1)")
	Collection<Lessor> maxRequestsDeniedLessor();
	@Query("Select l from Lessor l where l.pendingRequests=(select max(l1.pendingRequests) from Lessor l1)")
	Collection<Lessor> maxRequestsPendingLessor();
	@Query("Select l from Lessor l where l.ratioRequests=(select max(l1.ratioRequests) from Lessor l1)")
	Collection<Lessor> maxRatio();
	@Query("Select l from Lessor l where l.ratioRequests=(select min(l1.ratioRequests) from Lessor l1)")
	Collection<Lessor> minRatio();
}
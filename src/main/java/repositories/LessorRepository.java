package repositories;

import java.util.Collection;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Lessor;

@Repository
public interface LessorRepository extends JpaRepository<Lessor,Integer>{
	@Query("select t,(select count(r) from Lessor t2 join t2.properties  p join p.requests r where r.state = 'ACCEPTED' and t.id=t2.id) as mr from Lessor t order by mr DESC")
	Collection<Object[]> maxRequestsApprovedLessor();
	@Query("select t,(select count(r) from Lessor t2 join t2.properties  p join p.requests r where r.state = 'DENIED' and t.id=t2.id) as mr from Lessor t order by mr DESC")
	Collection<Object[]> maxRequestsDeniedLessor();
	@Query("select t,(select count(r) from Lessor t2 join t2.properties  p join p.requests r where r.state = 'PENDING' and t.id=t2.id) as mr from Lessor t order by mr DESC")
	Collection<Object[]> maxRequestsPendingLessor();
	@Query("select a from Lessor a where a.userAccount.id = ?1")
	Lessor findByUserAccountId(int userAccountId);
}
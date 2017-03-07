package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Audit;

import java.util.Collection;

@Repository
	public interface AuditRepository extends JpaRepository<Audit,Integer>{

		@Query("select a from Audit a where a.auditor.id=?1 and a.property.id=?2  and final=0")
		Collection<Audit> findAllDraftByAuditor(Integer id,Integer id2);

        @Query("select a from Audit a where a.auditor.id=?1 and a.property.id=?2 and final=1")
        Collection<Audit> findAllFinal(Integer id,Integer id2);

    @Query("select a from Audit a where a.auditor.id=?1 and a.property.id=?2")
        Audit findByAuditor(int id,int id2);
	}


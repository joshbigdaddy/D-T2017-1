package repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import security.UserAccount;


@Repository
public interface ActorRepository extends JpaRepository<Actor,Integer>{
	

	@Query("select a from Actor a where a.userAccount.id = ?1")
	Actor findByUserAccountId(int userAccountId);
	
	@Query("select min(a.socialIdentities.size) from Actor a")
	//select a.socialIdentities from Actor a
	Integer minSocialIdentitiesPerActor();
	@Query("select max(a.socialIdentities.size) from Actor a")
	Integer maxSocialIdentitiesPerActor();
	@Query("select avg(a.socialIdentities.size) from Actor a")
	Double avgSocialIdentitiesPerActor();

	@Query("select a from Actor a where a.userAccount = ?1 ")
	Actor findActorByUserAccount(UserAccount userAccount);
}

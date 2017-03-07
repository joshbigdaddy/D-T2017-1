package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import domain.Actor;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ActorService {
	
	// Managed repository -----------------------------------------------------

		@Autowired
		private ActorRepository actorRepository;	


		public ActorService() {
			super();
		}
		
		// Simple CRUD methods ----------------------------------------------------
		
		public Collection<Actor> findAll() {
			Collection<Actor> result;
			
			result = actorRepository.findAll();
			Assert.notNull(result);
			
			return result;
		}

		public Actor findOne(int actorId) {
			Assert.isTrue(actorId != 0);
			
			Actor result;

			result = actorRepository.findOne(actorId);
			Assert.notNull(result);

			return result;
		}
		
		public void save(Actor actor) {
			Assert.notNull(actor);

			actorRepository.save(actor);
		}	
		
		public void delete(Actor actor) {
			Assert.notNull(actor);
			Assert.isTrue(actor.getId() != 0);
			Assert.isTrue(actorRepository.exists(actor.getId()));		
			
			actorRepository.delete(actor);
		}


    public void register(Actor actor, String s) {
        Assert.notNull(actor);
        List<Authority> authorities = new ArrayList<>();
        Authority a = new Authority();
        a.setAuthority(s);
        authorities.add(a);
        actor.getUserAccount().setAuthorities(authorities);
        actorRepository.save(actor);
    }

	// Other business methods -------------------------------------------------

	public Actor findActorByPrincipal() {
		UserAccount userAccount = LoginService.getPrincipal();
		return actorRepository.findByUserAccountId(userAccount.getId());
	}
	
	
	
	
	
	
	//Query Methods for Dashboard
	public Integer minSocialIdentitiesPerActor(){
		return actorRepository.minSocialIdentitiesPerActor();
	}
	
	public Integer maxSocialIdentitiesPerActor(){
		return actorRepository.maxSocialIdentitiesPerActor();
	}
	
	
	public Double avgSocialIdentitiesPerActor(){
		return actorRepository.avgSocialIdentitiesPerActor();
	}
	
	
}

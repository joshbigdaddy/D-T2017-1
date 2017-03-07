package services;

import java.util.Collection;

import domain.Actor;
import domain.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ActorRepository;
import repositories.AuditRepository;
import domain.Audit;
import domain.CreditCard;

@Service
@Transactional
public class AuditService {

	// Managed repositories
	@Autowired
	private AuditRepository auditRepository;
	@Autowired
	private Validator validator;

	@Autowired
	private ActorService actorService;

	// Constructor
	public AuditService() {
		super();
	}

	// CRUD Methods

	public Collection<Audit> findAll() {
		Collection<Audit> result;
		result = auditRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Audit findOne(int auditId) {
		Assert.isTrue(auditId != 0);
		Audit result;
		result = auditRepository.findOne(auditId);
		Assert.notNull(result);
		return result;
	}

	public Audit save(Audit audit) {
		Assert.notNull(audit);
		return auditRepository.save(audit);
	}

	public void delete(Audit audit) {
		Assert.notNull(audit);
		Assert.isTrue(audit.getId() != 0);
		Assert.isTrue(auditRepository.exists(audit.getId()));
		auditRepository.delete(audit);
	}
	public Audit reconstruct(Audit audit, BindingResult bindingResult) {
		Audit result;

        if (audit.getId()==0){
            result = audit;
        }else {
            result = auditRepository.findOne(audit.getId());
            result.setText(audit.getText());
            result.setMoment(audit.getMoment());
            result.setAttachment(audit.getAttachment());

			validator.validate(result, bindingResult); 
        }
        


        return result;
    }


	public Collection<Audit> findAllDraftByAuditor(Property property){
		Actor actor = actorService.findActorByPrincipal();
		Assert.notNull(actor);
		Collection<Audit> result = auditRepository.findAllDraftByAuditor(actor.getId(),property.getId());

		return result;
	}

	public Collection<Audit> findAllFinal(Property property){
		Actor actor = actorService.findActorByPrincipal();
		Assert.notNull(actor);
		Collection<Audit> result = auditRepository.findAllFinal(actor.getId(),property.getId());

		return result;
	}

    public Audit findByAuditor(Actor actorByPrincipal,Property property) {
        Assert.notNull(actorByPrincipal);
        Assert.notNull(property);
        System.out.println(property);
        return auditRepository.findByAuditor(actorByPrincipal.getId(),property.getId());

    }
}

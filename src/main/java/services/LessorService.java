package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LessorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Lessor;
import domain.Property;
import domain.Request;
import domain.Request.RequestType;

@Service
@Transactional
public class LessorService {

	// Managed repositories
	@Autowired
	private LessorRepository lessorRepository;

	// Constructor
	public LessorService() {

		super();
	}

	// CRUD Methods
	public Collection<Lessor> findAll() {
		Collection<Lessor> result;
		result = lessorRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Lessor findOne(int lessorId) {
		Assert.isTrue(lessorId != 0);
		Lessor result;
		result = lessorRepository.findOne(lessorId);
		Assert.notNull(result);
		return result;
	}

	public void save(Lessor lessor) {
		Assert.notNull(lessor);
		lessorRepository.save(lessor);
	}

	public void delete(Lessor lessor) {
		Assert.notNull(lessor);
		Assert.isTrue(lessor.getId() != 0);
		Assert.isTrue(lessorRepository.exists(lessor.getId()));
		lessorRepository.delete(lessor);
	}

	public Double avgDeniedRequestsPerLessor() {
		/*List<Lessor> lessors = lessorRepository.findAll();
		int denied = 0;
		int total = 0;
		for (Lessor l : lessors) {
			for (Property p : l.getProperties()) {
				for (Request r : p.getRequests()) {
					if (r.getState() == RequestType.DENIED)
						denied++;
					total++;
				}
			}
		}
		if(total==0){
			return 0.;
		}else{
		return (double) (denied / total);
		}*/
		return 0.;
		}
	
	public Double avgAcceptedRequestsPerLessor() {
		List<Lessor> lessors = lessorRepository.findAll();
		/*int denied = 0;
		int total = 0;
		for (Lessor l : lessors) {
			for (Property p : l.getProperties()) {
				for (Request r : p.getRequests()) {
					if (r.getState() == RequestType.ACCEPTED)
						denied++;
					total++;
				}
			}
		}
		if(total==0){
			return 0.;
		}else{
		return (double) (denied / total);
		}*/
		return 0.;
	}

	public Collection<Request> getAllRequestsByLessor(int id){
		List<Request> result = new ArrayList<>();
		Lessor lessor = lessorRepository.findOne(id);
		Assert.notNull(lessor);
		for(Property p:lessor.getProperties()){
			result.addAll(p.getRequests());
		}
		Assert.notNull(result);

		return result;
	}

    public Collection<Request> getAllRequestsPendingByLessor(int id){
        List<Request> result = new ArrayList<>();
        Lessor lessor = lessorRepository.findOne(id);
        Assert.notNull(lessor);
        for(Property p:lessor.getProperties()) {
            for (Request r : p.getRequests()) {
                if (r.getState() == RequestType.PENDING) {
                    result.add(r);
                }
            }
        }
        Assert.notNull(result);
        return result;
    }
	public Lessor findLessorByPrincipal() {
		Lessor result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = lessorRepository.findByUserAccountId(userAccount.getId());

		return result;
		}
	
	public Collection<Object[]> maxRequestsApprovedLessor(){
		return lessorRepository.maxRequestsApprovedLessor();
	}

	public Collection<Object[]> maxRequestsDeniedLessor(){
		return lessorRepository.maxRequestsDeniedLessor();
	}

	public Collection<Object[]> maxRequestsPendingLessor(){
		return lessorRepository.maxRequestsPendingLessor();
	}

	public Collection<Lessor> leesorRatioMaxVsMin() {
	/*	// TODO Auto-generated method stub
		Collection<Request> denied=new ArrayList<Request>();
		Collection<Request> accepted=new ArrayList<Request>();
		for(Lessor l:findAll()){
			
			for(Property p: l.getProperties()){
				denied.addAll(p.getRequests());
				accepted.addAll(p.getRequests());
				for(Request rq:denied){
					if(rq.getState()!=RequestType.DENIED){
						denied.remove(rq);
					}
				}
				for(Request rq:accepted){
					if(rq.getState()!=RequestType.ACCEPTED){
						accepted.remove(rq);
					}
				}
				
				
			}
			
		}*/
		return findAll();
	}
}

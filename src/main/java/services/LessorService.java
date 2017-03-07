package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Collection<Lessor> lessors = findAll();
		double denied = 0;
		double total = 0;
		for (Lessor l : lessors) {
			for (Property p : l.getProperties()) {
				for (Request r : p.getRequests()) {
					if (r.getState() == RequestType.DENIED)
						denied+=1.;
					total+=1.;
				}
			}
		}
		if(total==0){
			return 0.;
		}else{
		return  (denied / total);
		}

		}
	
	public Double avgAcceptedRequestsPerLessor() {
		Collection<Lessor> lessors = findAll();
		double denied = 0;
		double total = 0;
		for (Lessor l : lessors) {
			for (Property p : l.getProperties()) {
				for (Request r : p.getRequests()) {
					if (r.getState() == RequestType.ACCEPTED)
						denied+=1.;
					total+=1.;
				}
			}
		}
		if(total==0){
			return 0.;
		}else{
		return  (denied / total);
		}
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
    public Collection<Request> getAllRequestsAcceptedByLessor(int id){
        List<Request> result = new ArrayList<>();
        Lessor lessor = lessorRepository.findOne(id);
        Assert.notNull(lessor);
        for(Property p:lessor.getProperties()) {
            for (Request r : p.getRequests()) {
                if (r.getState() == RequestType.ACCEPTED) {
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

	public Map<Lessor, Double> lessorRatioMaxVsMin() {
		
		Collection<Lessor> lessors=findAll();
		Map<Lessor, Double> map=new HashMap<Lessor,Double>();
		for(Lessor l:lessors){
			double resultado=getAllRequestsAcceptedByLessor(l.getId()).size()/getAllRequestsAcceptedByLessor(l.getId()).size();
			map.put(l, resultado);
		}
		return map;
	}
	public Collection<Lessor> minRatio(){
		Map<Lessor, Double> map=lessorRatioMaxVsMin();
		Collection<Lessor> lessors=map.keySet();

		Collection<Lessor> lessorsRes=new ArrayList<Lessor>();
		double i=123456.;
		for(Lessor l:lessors){
			if(i==123456.){
				i= map.get(l);
				lessorsRes.add(l);
			}else{
				double e=map.get(l);
				if(e<i){
					lessorsRes.clear();
					lessorsRes.add(l);
					i=e;
				}else if(e==i){
					lessorsRes.add(l);
				}
			}
		}
		return lessorsRes;
	}
	public Collection<Lessor> maxRatio(){
		Map<Lessor, Double> map=lessorRatioMaxVsMin();
		Collection<Lessor> lessors=map.keySet();

		Collection<Lessor> lessorsRes=new ArrayList<Lessor>();
		double i=123456.;
		for(Lessor l:lessors){
			if(i==123456.){
				i= map.get(l);
				lessorsRes.add(l);
			}else{
				double e=map.get(l);
				if(e>i){
					lessorsRes.clear();
					lessorsRes.add(l);
					i=e;
				}else if(e==i){
					lessorsRes.add(l);
				}
			}
		}
		return lessorsRes;
	}
}

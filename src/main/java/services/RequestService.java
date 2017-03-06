package services;

import java.util.Collection;

import domain.Configuration;
import domain.Lessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import domain.Request;

@Service
@Transactional
public class RequestService {

	// Managed repositories
	@Autowired
	private RequestRepository requestRepository;

	@Autowired
    private ConfigurationService configurationService;

	@Autowired
    private LessorService lessorService;

	@Autowired
    private TenantService tenantService;
	// Constructor
	public RequestService() {
		super();
	}


	// CRUD Methods

	public Collection<Request> findAll() {
		Collection<Request> result;
		result = requestRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Request findOne(int requestId) {
		Assert.isTrue(requestId != 0);
		Request result;
		result = requestRepository.findOne(requestId);
		Assert.notNull(result);
		return result;
	}

	public void save(Request request) {
		Assert.notNull(request);
		requestRepository.save(request);
	}

	public void delete(Request request) {
		Assert.notNull(request);
		Assert.isTrue(request.getId() != 0);
		Assert.isTrue(requestRepository.exists(request.getId()));
		requestRepository.delete(request);
	}

    public void accept(Request request) {
		Assert.notNull(request);

		request.setState(Request.RequestType.ACCEPTED);
		chargeFeeLessor(request.getProperty().getLessor());
		tenantService.chargeTenant(request);
		lessorService.save(request.getProperty().getLessor());

		save(request);
    }

    private void chargeFeeLessor(Lessor lessor){
        Double fee = configurationService.getFirstConfiguration().getFee();
        Double actualFee = lessor.getCreditCard().getFee();
        lessor.getCreditCard().setFee(actualFee+fee);
    }

	public void reject(Request request) {
		Assert.notNull(request);

		request.setState(Request.RequestType.DENIED);

		save(request);
	}
}

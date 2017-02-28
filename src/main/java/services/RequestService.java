package services;

import java.util.Collection;

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
}

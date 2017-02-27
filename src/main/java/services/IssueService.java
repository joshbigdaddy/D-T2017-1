package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.IssueRepository;

@Service
@Transactional
public class IssueService {

	@Autowired
	private IssueRepository issueRepository;

	public IssueService() {
		super();
	}

}

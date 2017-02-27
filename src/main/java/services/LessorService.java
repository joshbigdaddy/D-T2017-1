package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.LessorRepository;

@Service
@Transactional
public class LessorService {

	@Autowired
	private LessorRepository lessorRepository;

	public LessorService() {

		super();
	}

}

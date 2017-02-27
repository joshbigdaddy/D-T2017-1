package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CommentRepository;

@Service
@Transactional
public class CommentService {
	
	@Autowired
	private CommentRepository commentReposiroty;
	
	public CommentService(){
		super();
	}

}

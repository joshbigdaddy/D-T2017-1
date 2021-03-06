package services;

import java.util.Collection;
import java.util.Date;

import domain.SocialUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;

@Service
@Transactional
public class CommentService {

	// Managed repositories
	@Autowired
	private CommentRepository commentReposiroty;

	@Autowired
	private ActorService actorService;

	// Constructor
	public CommentService() {
		super();
	}

	// CRUD Methods

	public Collection<Comment> findAll() {
		Collection<Comment> result;
		result = commentReposiroty.findAll();
		Assert.notNull(result);
		return result;
	}

	public Comment findOne(int commentId) {
		Assert.isTrue(commentId != 0);
		Comment result;
		result = commentReposiroty.findOne(commentId);
		Assert.notNull(result);
		return result;
	}

	public void save(Comment comment) {
		Assert.notNull(comment);
		commentReposiroty.save(comment);
	}

	public void delete(Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);
		Assert.isTrue(commentReposiroty.exists(comment.getId()));
		commentReposiroty.delete(comment);
	}

    public void newComment(Comment comment, SocialUser socialUser,SocialUser actor) {
		Assert.notNull(comment);
		Assert.notNull(socialUser);
		Assert.notNull(actor);
		comment.setMoment(new Date());
		comment.setAuthor(socialUser);
		comment.setStarRating(0);
		comment.setReceiver(actor);
		save(comment);

    }
}

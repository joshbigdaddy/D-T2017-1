package domain;
import java.util.Collection;

import javax.persistence.*;


@Entity
	@Access(AccessType.PROPERTY)
	public class SocialUser  extends Actor{

		public SocialUser(){
			super();
		
		}

	//RELATIONSHIPS
	private Collection<Comment> comments;
	private CreditCard creditCard;
	@OneToMany(mappedBy = "socialUser")
	public Collection<Comment> getComments(){
	return comments;
	}
	public void setComments(Collection<Comment> comments){
		this.comments=comments;
	}
	@OneToOne(cascade = CascadeType.ALL)
	public CreditCard getCreditCard(){
	return this.creditCard;
	}

	public void setCreditCard(CreditCard creditCard){
		this.creditCard=creditCard;
	}

	private Collection<Comment> commentsReceived;

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Comment> getCommentsReceived() {
		return commentsReceived;
	}

	public void setCommentsReceived(Collection<Comment> commentsReceived) {
		this.commentsReceived = commentsReceived;
	}
}
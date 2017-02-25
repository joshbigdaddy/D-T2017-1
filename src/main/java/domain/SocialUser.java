package domain;
import java.util.Collection;

import javax.persistence.Access;
	import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;



	@Entity
	@Access(AccessType.PROPERTY)
	public class SocialUser  extends Actor{

		public SocialUser(){
			super();
		
		}

	//RELATIONSHIPS
	private Collection<Comment> comments;	
	private Collection<CreditCard> creditCards;
	@OneToMany()
	public Collection<Comment> getComments(){
	return comments;	
	}
	public void setComments(Collection<Comment> comments){
		this.comments=comments;
	}
	@OneToMany()
	public Collection<CreditCard> getCreditCards(){
	return creditCards;	
	}
	public void setCreditCards(Collection<CreditCard> creditCards){
		this.creditCards=creditCards;
	}
	
}
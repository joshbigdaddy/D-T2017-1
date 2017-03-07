package domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	public Comment() {
		
		this.moment = new Date();
	}

	private Date moment;
	private String title;
	private String text;
	private Integer starRating;
	private SocialUser author;
	private SocialUser receiver;

	
	@NotNull
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Range(min = 0, max = 5)
	@NotNull
	public Integer getStarRating() {
		return starRating;
	}

	public void setStarRating(Integer starRating) {
		this.starRating = starRating;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public SocialUser getAuthor() {
		return author;
	}

	public void setAuthor(SocialUser socialUser) {
		this.author = socialUser;
	}

	@ManyToOne(cascade = CascadeType.ALL)
    public SocialUser getReceiver() {
        return receiver;
    }

    public void setReceiver(SocialUser receiver) {
        this.receiver = receiver;
    }
}

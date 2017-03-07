package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity {

	public Actor() {
		super();
		socialIdentities=new ArrayList<SocialIdentity>();

	}

	private String name;
	private String surname;
	private String email;
	private String phone;
	private String picture;

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Pattern(regexp = "^(\\+(?:00[1-9]|0[1-9]\\d|[1-9]\\d\\d)$*(\\((?:00[1-9]|0[1-9]\\d|[1-9]\\d\\d)$*\\)))?[0]?[a-zA-Z0-9\\- ]*$")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@URL
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	private Collection<SocialIdentity> socialIdentities;

	@OneToMany(mappedBy = "actor")
	public Collection<SocialIdentity> getSocialIdentities() {
		return socialIdentities;
	}

	public void setSocialIdentities(Collection<SocialIdentity> socialIdentities) {
		this.socialIdentities = socialIdentities;
	}

	private UserAccount userAccount;

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public String toString() {
		return "Actor{" +
				"name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", picture='" + picture + '\'' +
				", socialIdentities=" + socialIdentities +
				", userAccount=" + userAccount +
				'}';
	}
}

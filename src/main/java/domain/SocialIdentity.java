package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SocialIdentity extends DomainEntity {

	public SocialIdentity() {

	}

	private String nick;
	private String name;
	private String link;
	private String picture;

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(unique = true)
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@URL
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	// private ClasePrueba claseprueba;
	// private Collection<ClasePrueba> clasesPruebas;

	/*
	 * @OneToOne() public ClasePrueba getClasePrueba() { return claseprueba; }
	 * 
	 * public void setClasePrueba(ClasePrueba claseprueba) { this.claseprueba =
	 * claseprueba; }
	 * 
	 * 
	 * @OneToMany public ClasePrueba getClasePrueba() { return claseprueba; }
	 * 
	 * public void setClasePrueba(ClasePrueba claseprueba) { this.claseprueba =
	 * claseprueba; }
	 * 
	 * @ManyToOne public ClasePrueba getClasePrueba() { return claseprueba; }
	 * 
	 * public void setClasePrueba(ClasePrueba claseprueba) { this.claseprueba =
	 * claseprueba; }
	 */

}

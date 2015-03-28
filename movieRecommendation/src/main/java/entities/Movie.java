package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


@Entity
@Table(name = "MOVIE")
public class Movie extends Item implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Column(name = "NAME")
	public String name;
	
	@Column(name = "YEAR")
	public Long releaseYear;
	
	@JoinColumn(name = "DIRCTR_ID")
	@ManyToOne
	public Director director;
	
	@JoinColumn(name = "RATE_ID")
	@OneToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	public Rate rate;


	public Long getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Long releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}



}
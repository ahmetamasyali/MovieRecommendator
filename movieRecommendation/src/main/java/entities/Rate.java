package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "RATE")
public class Rate extends Item implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	@Column(name = "RATNG")
	private Double rating;
	
	@Column(name = "VOTE_COUNT")
	private Long voteCount;
	


	public Long getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Long voteCount) {
		this.voteCount = voteCount;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}



}
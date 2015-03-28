package dao.impl;

import dao.MovieDao;
import entities.Movie;

public class MovieDaoImpl extends AbstractDaoImpl<Movie> implements MovieDao {
	public MovieDaoImpl() {
		super(Movie.class);
	}




	public Movie createPersistentObject() {
		return new Movie();
	}

	
}

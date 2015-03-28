package dao.impl;

import dao.RateDao;
import entities.Rate;

public class RateDaoImpl extends AbstractDaoImpl<Rate> implements RateDao {
	public RateDaoImpl() {
		super(Rate.class);
	}


	public Rate createPersistentObject() {
		return new Rate();
	}

}

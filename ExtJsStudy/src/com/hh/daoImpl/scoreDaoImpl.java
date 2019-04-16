package com.hh.daoImpl;

import com.hh.dao.ScoreDao;
import com.hh.domain.Score;
public class scoreDaoImpl extends DaoImpl<Score> implements ScoreDao{
	
public scoreDaoImpl() {
		super(Score.class);
		
	}

@Override
	public Score queryById(int id) {
		return  getHibernateTemplate().get(Score.class, id);
	
		
	}

	

}

package com.hh.serviceImpl;



import com.hh.dao.ScoreDao;
import com.hh.domain.Score;
import com.hh.service.ScoreService;

public class ScoreServiceImpl extends BaseServiceImpl<Score> implements ScoreService  {
	private ScoreDao scoreDao;
	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
		this.dao = scoreDao;
	}
	@Override
	public void AddScore(Score score) {
	
		
	}
	
	

}

package com.hh.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.hh.dao.RepertoryDao;
import com.hh.domain.Repertory;
public class repertoryDaoImpl extends DaoImpl<Repertory> implements RepertoryDao{

	public repertoryDaoImpl() {
		super(Repertory.class);
		
	}

	@Override
	public Repertory queryByName(Repertory repertory) {
		
		String hql = "from Repertory where repertory_name = ?";
		List<Repertory> list = this.getHibernateTemplate().find(hql, repertory.getRepertory_name());
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
		
	}

	@Override
	public List<Repertory> queryByPageAndKey(int begin,int pageSize,Repertory repertory){
		DetachedCriteria criteria = DetachedCriteria.forClass(Repertory.class).add(Restrictions.like("repertory_name", "%"+repertory.getRepertory_name()+"%"));
		List<Repertory> list = this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	@Override
	public long findCountByKey(Repertory repertory) {
		String hql = "from Repertory it  where 1=1"+" and it.repertory_name like '%"+repertory.getRepertory_name()+"%'";
		List<Repertory> list=getSession().createQuery(hql).list();
		return list.size();
	}

	
	
	

}

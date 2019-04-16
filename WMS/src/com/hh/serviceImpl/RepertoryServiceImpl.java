package com.hh.serviceImpl;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.hh.dao.RepertoryDao;
import com.hh.domain.PageBean;
import com.hh.domain.Repertory;
import com.hh.service.RepertoryService;

public class RepertoryServiceImpl extends BaseServiceImpl<Repertory> implements RepertoryService  {
	private RepertoryDao repertoryDao;
	public void setRepertoryDao(RepertoryDao repertoryDao) {
		this.repertoryDao = repertoryDao;
		this.dao = repertoryDao;
	}
	@Override
	public Repertory queryByName(Repertory repertory) {
		
		return repertoryDao.queryByName(repertory); 
	}
	@Override
	public PageBean<Repertory> queryByPageAndKey(int begin, int pageSize, Repertory repertory) {
		PageBean<Repertory> pageBean = new PageBean<Repertory>();
		// 封装当前页数
		
		// 封装每页显示的记录数
		pageBean.setPageSize(pageSize);
		// 封装总记录数
		long totalCount = repertoryDao.findCountByKey(repertory);
		pageBean.setTotalCount(totalCount);
		// 封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 封装每页显示的数据
		List<Repertory> list = repertoryDao.queryByPageAndKey(begin, pageSize, repertory);
		pageBean.setList(list);
		return pageBean;
		
	}
	


	

}

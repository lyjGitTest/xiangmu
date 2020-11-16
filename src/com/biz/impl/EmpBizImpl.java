package com.biz.impl;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.lang.model.type.ArrayType;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.IEmpBiz;
import com.pojo.EmpWelfare;
import com.pojo.Emplyee;
import com.pojo.PageBean;
import com.pojo.Salary;
import com.pojo.Welfare;
import com.service.DaoService;
@Service("EmpBiz")
@Transactional
public class EmpBizImpl implements IEmpBiz {
@Resource(name="DaoService")
private DaoService daoService;
	public DaoService getDaoService() {
	return daoService;
}

public void setDaoService(DaoService daoService) {
	this.daoService = daoService;
}

	@Override
	public boolean save(Emplyee emp) {
		// TODO Auto-generated method stub
	
		int num=daoService.getEmplyeeMapper().save(emp);
		if(num>0){
			System.out.println("num-----"+num);
			Integer id=daoService.getEmplyeeMapper().findMaxId();

			//閫氳繃id娣诲姞钖祫
			Salary sa=new Salary(id,emp.getEmoney());

			//淇濆瓨钖祫
			daoService.getSalaryMapper().save(sa);
			
		   //绂忓埄
			String[] wids=emp.getWids();

			if(wids.length>0 && wids!=null) {
				System.out.println("wids.length"+wids.length);
				for (int i = 0; i < wids.length; i++) {
					EmpWelfare ef = new EmpWelfare(id, new Integer(wids[i]));

					daoService.getEmpwelfareMapper().save(ef);
				}
			}

			return true;
		}
		return false;
	}

	@Override
	public boolean update(Emplyee emp) {
		// TODO Auto-generated method stub
		int num=daoService.getEmplyeeMapper().update(emp);
		//鍒ゆ柇鍘熸潵鏈夎柂璧�
		if(num>0){
			Salary oldsa=daoService.getSalaryMapper().findById(emp.getEid());
			if(oldsa!=null && oldsa.getEmoney()!=null){
				//淇敼钖祫
				oldsa.setEmoney(emp.getEmoney());
				daoService.getSalaryMapper().update(oldsa);
			}
		}else{
			Salary sa=new Salary(emp.getEid(),emp.getEmoney());
			daoService.getSalaryMapper().save(sa);
		}
		
		//绂忓埄
	List<Welfare> fl=daoService.getEmpwelfareMapper().findAlllef(emp.getEid());
	if(fl.size()>0 && fl!=null){
		//鍒犻櫎
		daoService.getEmpwelfareMapper().delete(emp.getEid());
	}else{
		//鏂板缓绂忓埄
		String wids[]=emp.getWids();
		if(wids.length>0 && wids!=null){
			for(int i=0;i<wids.length;i++){
				EmpWelfare ewf=new EmpWelfare(emp.getEid(),new Integer(wids[i]));

				daoService.getEmpwelfareMapper().save(ewf);

			}
		}
		return true;
	}
		return false;
	}

	@Override
	public boolean delete(Integer eid) {
		// TODO Auto-generated method stub
		daoService.getSalaryMapper().delete(eid);
		daoService.getEmpwelfareMapper().delete(eid);
		int num=daoService.getEmplyeeMapper().delete(eid);
		if(num>0){
			return true;
		}
		return false;
	}
	@Override
	public Emplyee findById(Integer eid) {
		// TODO Auto-generated method stub
		Emplyee emp=daoService.getEmplyeeMapper().findById(eid);
		
		Salary sa=daoService.getSalaryMapper().findById(eid);
		if(sa!=null && sa.getEmoney()!=null){
			emp.setEmoney(sa.getEmoney());
		}
		List<Welfare> lwf=daoService.getEmpwelfareMapper().findAlllef(eid);

		if(lwf.size()>0 && !lwf.equals("")){
			//鍒涘缓绂忓埄
			String wids[]=new String[lwf.size()];
			for(int i=0;i<lwf.size();i++){
				Welfare wf=lwf.get(i);
				wids[i]=wf.getWid().toString();
			}
			emp.setWids(wids);
		}
		emp.setLswf(lwf);
		return emp;
	}

	@Override
	public List<Emplyee> findAll(PageBean pb) {
		// TODO Auto-generated method stub
		if(pb!=null){
			return daoService.getEmplyeeMapper().findPageAll(pb);
		}
		return null;
	}

	@Override
	public int findMaxRows() {
		// TODO Auto-generated method stub
		return daoService.getEmplyeeMapper().findMaxRows();
	}

}

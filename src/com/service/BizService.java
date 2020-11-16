package com.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.biz.IDepBiz;
import com.biz.IEmpBiz;
import com.biz.IWelfareBiz;

@Service("BizService")
public class BizService {
	@Resource(name="DepBiz")
private IDepBiz depbiz;
	@Resource(name="EmpBiz")
	private IEmpBiz iEmpBiz;
	@Resource(name="WelfareBiz")
	private IWelfareBiz iWelfareBiz;
	public IDepBiz getDepbiz() {
		return depbiz;
	}
	public void setDepbiz(IDepBiz depbiz) {
		this.depbiz = depbiz;
	}
	public IEmpBiz getiEmpBiz() {
		return iEmpBiz;
	}
	public void setiEmpBiz(IEmpBiz iEmpBiz) {
		this.iEmpBiz = iEmpBiz;
	}
	public IWelfareBiz getiWelfareBiz() {
		return iWelfareBiz;
	}
	public void setiWelfareBiz(IWelfareBiz iWelfareBiz) {
		this.iWelfareBiz = iWelfareBiz;
	}
	
}

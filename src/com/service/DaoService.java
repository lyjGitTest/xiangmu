package com.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mapper.IDepMapper;
import com.mapper.IEmpWelfareMapper;
import com.mapper.IEmplyeeMapper;
import com.mapper.ISalaryMapper;
import com.mapper.IWelfareMapper;

@Service("DaoService")
public class DaoService {
	@Resource(name="DepDao")
private IDepMapper depMapper;
	@Resource(name="EmpDao")
	private IEmplyeeMapper emplyeeMapper;
	@Resource(name="EmpWelfareDao")
	private IEmpWelfareMapper empwelfareMapper;
	@Resource(name="SalaryDao")
	private ISalaryMapper salaryMapper;
	@Resource(name="welfareDao")
	private IWelfareMapper welfareMapper;
	public IDepMapper getDepMapper() {
		return depMapper;
	}
	public void setDepMapper(IDepMapper depMapper) {
		this.depMapper = depMapper;
	}
	public IEmplyeeMapper getEmplyeeMapper() {
		return emplyeeMapper;
	}
	public void setEmplyeeMapper(IEmplyeeMapper emplyeeMapper) {
		this.emplyeeMapper = emplyeeMapper;
	}

	public IEmpWelfareMapper getEmpwelfareMapper() {
		return empwelfareMapper;
	}

	public void setEmpwelfareMapper(IEmpWelfareMapper empwelfareMapper) {
		this.empwelfareMapper = empwelfareMapper;
	}

	public ISalaryMapper getSalaryMapper() {
		return salaryMapper;
	}
	public void setSalaryMapper(ISalaryMapper salaryMapper) {
		this.salaryMapper = salaryMapper;
	}
	public IWelfareMapper getWelfareMapper() {
		return welfareMapper;
	}
	public void setWelfareMapper(IWelfareMapper welfareMapper) {
		this.welfareMapper = welfareMapper;
	}
	
	

}

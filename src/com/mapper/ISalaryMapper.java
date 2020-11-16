package com.mapper;


import org.springframework.stereotype.Service;

import com.pojo.Salary;

@Service("SalaryDao")
public interface ISalaryMapper {
public int save(Salary sa);
public int delete(Integer eid);
public int update(Salary sa);
public Salary findById(Integer eid);
//public List<Salary> findAlllsy();
}

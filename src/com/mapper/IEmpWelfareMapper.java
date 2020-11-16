package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.EmpWelfare;
import com.pojo.Welfare;

@Service("EmpWelfareDao")
public interface IEmpWelfareMapper {
public int save(EmpWelfare ewf);
public int delete(Integer eid);
public List<Welfare> findAlllef(Integer eid);
}

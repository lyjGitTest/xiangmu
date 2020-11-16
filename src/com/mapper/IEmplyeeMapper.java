package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.Emplyee;
import com.pojo.PageBean;
@Service("EmpDao")
public interface IEmplyeeMapper {
public int save(Emplyee emp);
public int update(Emplyee emp);
public int delete(Integer eid);
public Emplyee findById(Integer eid);
public List<Emplyee> findPageAll(PageBean pb);
public int findMaxRows();
public int findMaxId();
}

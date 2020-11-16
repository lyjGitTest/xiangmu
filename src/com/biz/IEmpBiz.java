package com.biz;

import java.util.List;

import com.pojo.Emplyee;
import com.pojo.PageBean;

public interface IEmpBiz {
public boolean save(Emplyee emp);
public boolean update(Emplyee emp);
public boolean delete(Integer eid);
public Emplyee findById(Integer eid);
public List<Emplyee> findAll(PageBean pb);
public int findMaxRows();
}

package com.mapper;



import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.Dep;
@Service("DepDao")
public interface IDepMapper {
public List<Dep> findAll(); 
}

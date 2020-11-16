package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.Welfare;

@Service("welfareDao")
public interface IWelfareMapper {
public List<Welfare> findAlllwf();
}

package com.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.pojo.Dep;
import com.pojo.Emplyee;
import com.pojo.PageBean;
import com.pojo.Welfare;
import com.service.BizService;
import com.utils.AjaxUtils;
@Controller
public class EmpAction implements IAction {
	@Resource(name="BizService")
private BizService bizService;
	
	public BizService getBizService() {
		return bizService;
	}

	public void setBizService(BizService bizService) {
		this.bizService = bizService;
	}
@RequestMapping(value="save.do")
	@Override
	public String save(HttpServletRequest request, HttpServletResponse response, Emplyee emp) {
		// TODO Auto-generated method stub
	String realpath=request.getRealPath("/");
	MultipartFile multipartFile=emp.getPic();
	if(multipartFile!=null && !multipartFile.isEmpty()){
	String fname=multipartFile.getOriginalFilename();
	if(fname.lastIndexOf(".")!=-1){
	String txt=fname.substring(fname.lastIndexOf("."));
	if(txt.equalsIgnoreCase(".jpg")){
		String newfname=new Date().getTime()+txt;
		File file=new File(realpath+"/uppic/"+newfname);
	try {
		FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),file);
		emp.setPhoto(newfname);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	}
	}
	boolean flag=bizService.getiEmpBiz().save(emp);
	System.out.println("*****************************"+flag);
	if(flag){
		AjaxUtils.printString(response, 1+"");
	}else{
		AjaxUtils.printString(response, 0+"");
	}
	return null;
	}
@RequestMapping(value="update.do")
	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Emplyee emp) {
		// TODO Auto-generated method stub
	//获取原来图片
	String oldphoto=bizService.getiEmpBiz().findById(emp.getEid()).getPhoto();
	String realpath=request.getRealPath("/");
	MultipartFile multipartFile=emp.getPic();
	if(multipartFile!=null && !multipartFile.isEmpty()){
		String fname=multipartFile.getOriginalFilename();
		if(fname.lastIndexOf(".")!=-1){
			String txt=fname.substring(fname.lastIndexOf("."));
			if(txt.equalsIgnoreCase(".jpg")){
				String newfname=new Date().toString()+txt;
				File file=new File(realpath+"/uppic/"+fname);
				try {
					FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
					emp.setPhoto(newfname);
					
					//删除原来图片
					File oldfile=new File(realpath+"/uppic/"+oldphoto);
					if(oldfile.exists() && !oldphoto.equalsIgnoreCase("default.jpg")){
						oldfile.delete();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}else{
		emp.setPhoto(oldphoto);
	}
	boolean flag=bizService.getiEmpBiz().update(emp);
	if(flag){
		AjaxUtils.printString(response, 1+"");
	}else{
		AjaxUtils.printString(response, 0+"");
	}
	return null;
	}
@RequestMapping(value="delete.do")
	@Override
	public String delById(HttpServletRequest request, HttpServletResponse response, Integer eid) {
	boolean flag=bizService.getiEmpBiz().delete(eid);
	if(flag){
		AjaxUtils.printString(response, 1+"");
	}else{
		AjaxUtils.printString(response, 0+"");
	}
	return null;
	}
@RequestMapping(value="findById.do")
	@Override
	public String findById(HttpServletRequest request, HttpServletResponse response, Integer eid) {
		// TODO Auto-generated method stub
	Emplyee emp=bizService.getiEmpBiz().findById(eid);
	PropertyFilter propertyFilter=AjaxUtils.filterProperts("birthday","pic");
	String s=JSONObject.toJSONString(emp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	AjaxUtils.printString(response, s);
		return null;
	}
@RequestMapping(value="findDetail.do")
	@Override
	public String findDetail(HttpServletRequest request, HttpServletResponse response, Integer eid) {
		// TODO Auto-generated method stub
	Emplyee emp=bizService.getiEmpBiz().findById(eid);
	PropertyFilter propertyFilter=AjaxUtils.filterProperts("birthday","pic");
	String s=JSONObject.toJSONString(emp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	AjaxUtils.printString(response, s);
		return null;
	}
@RequestMapping(value="findpageAll.do")
	@Override
	public String findPageAll(HttpServletRequest request, HttpServletResponse response, Integer page, Integer rows) {
		// TODO Auto-generated method stub
	Map<String,Object> map=new HashMap<String,Object>();
	PageBean pb=new PageBean();
	page=page==null||page<1?pb.getPage():page;
	rows=rows==null||rows<1?pb.getRows():rows;
	if(rows>7)rows=7;
	pb.setPage(page);
	pb.setRows(rows);
	List<Emplyee> lsmp=bizService.getiEmpBiz().findAll(pb);
	int maxRows=bizService.getiEmpBiz().findMaxRows();
	
	map.put("page", page);
	map.put("total", maxRows);
	map.put("rows", lsmp);
	
	PropertyFilter propertyFilter=AjaxUtils.filterProperts("birthday","pic");
	String s=JSONObject.toJSONString(map,propertyFilter, SerializerFeature.DisableCircularReferenceDetect);
	AjaxUtils.printString(response, s);
		return null; 
	}
@RequestMapping(value="doinit.do")
	@Override
	public String doinit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	Map<String, Object> map=new HashMap<>();
	
	List<Dep> lsdep=bizService.getDepbiz().findAll();
	List<Welfare> lswf =bizService.getiWelfareBiz().findAll();
	map.put("lsdep", lsdep);
	map.put("lswf", lswf);
	
	PropertyFilter propertyFilter=AjaxUtils.filterProperts("birthday","pic");
	String s=JSONObject.toJSONString(map, propertyFilter, SerializerFeature.DisableCircularReferenceDetect);
	AjaxUtils.printString(response, s);
		return null;
	}

}

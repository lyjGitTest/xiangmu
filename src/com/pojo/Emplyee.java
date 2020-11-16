package com.pojo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Emplyee implements Serializable{
private Integer eid;
public Emplyee(Integer eid, String ename, String sex, String address, Date birthday, String photo, Integer depid) {
	super();
	this.eid = eid;
	this.ename = ename;
	this.sex = sex;
	this.address = address;
	this.birthday = birthday;
	this.photo = photo;
	this.depid = depid;
}
private String ename;
private String sex;
private String address;
private Date birthday;
private String photo;
private Integer depid;

public Integer getEid() {
	return eid;
}
public Emplyee() {
	super();
	// TODO Auto-generated constructor stub
}
public void setEid(Integer eid) {
	this.eid = eid;
}
public String getEname() {
	return ename;
}
public void setEname(String ename) {
	this.ename = ename;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}
public Integer getDepid() {
	return depid;
}
public void setDepid(Integer depid) {
	this.depid = depid;
}
public String getDepname() {
	return depname;
}
public void setDepname(String depname) {
	this.depname = depname;
}
public Float getEmoney() {
	return emoney;
}
public void setEmoney(Float emoney) {
	this.emoney = emoney;
}
public String getNewdate() {
	
	if(birthday!=null){
		newdate=new SimpleDateFormat("yyyy-MM-dd").format(birthday);
		
	}
	return newdate;
}
public void setNewdate(String newdate) {
	if(newdate!=null && !newdate.trim().equals("")){
		try {
			birthday=new SimpleDateFormat("yyyy-MM-dd").parse(newdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	this.newdate = newdate;
}
public MultipartFile getPic() {
	return pic;
}
public void setPic(MultipartFile pic) {
	this.pic = pic;
}
public String[] getWids() {
	return wids;
}
public void setWids(String[] wids) {
	this.wids = wids;
}
public List<Welfare> getLswf() {
	return lswf;
}
public void setLswf(List<Welfare> lswf) {
	this.lswf = lswf;
}
private String depname;
private Float emoney;

private String newdate;
private MultipartFile pic;

private String[] wids;
private List<Welfare> lswf;
}

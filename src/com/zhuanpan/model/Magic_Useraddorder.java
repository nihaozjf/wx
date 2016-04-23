package com.zhuanpan.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.Table;

import com.wx.model.WxUserInfo;
@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)  
@Table(name = "magic_useraddorder")
public class Magic_Useraddorder {
	private Integer id;
	private int aid ;
	private int prizeid;
	
	public Magic_Useraddorder(){
		
	}
	public Magic_Useraddorder(int aid,int prizeid){
		this.aid=aid;
		this.prizeid=prizeid;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "aid")
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	@Column(name = "prizeid")
	public int getPrizeid() {
		return prizeid;
	}
	public void setPrizeid(int prizeid) {
		this.prizeid = prizeid;
	}

	
	

}

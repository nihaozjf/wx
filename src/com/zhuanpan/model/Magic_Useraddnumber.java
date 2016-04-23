package com.zhuanpan.model;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)  
@Table(name = "magic_useraddnumber")

public class Magic_Useraddnumber {
	private Integer id;
	private int aid;
	private int number;
	
	public Magic_Useraddnumber(){
		
	}
	public Magic_Useraddnumber(int aid, int number){
		this.aid=aid;
		this.number=number;
		
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
	@Column(name = "number")
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	

}

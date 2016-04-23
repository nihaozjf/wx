package com.wx.model;

import static javax.persistence.GenerationType.IDENTITY;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)  
@Table(name = "wxuserinfo")

public class WxUserInfo implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String openId;
	private String nickName;
	private int sex;
	private String country;
	private String province;
	private String city;
	private String headImgUrl;
	
	
	public WxUserInfo(){
		
	}
	public WxUserInfo(String openId){
		this.openId=openId;
	}
	public WxUserInfo(String openId,String nickName,int sex,String country,
			String province,String city,String headImgUrl){
		this.openId=openId;
		this.nickName=nickName;
		this.sex=sex;
		this.country=country;
		this.province=province;
		this.city=city;
		this.headImgUrl=headImgUrl;
		
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
	@Column(name = "openid")
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	@Column(name = "nickname")
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Column(name = "sex")
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	@Column(name = "country")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Column(name = "province")
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name = "headimgurl")
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	
	
	
	

}

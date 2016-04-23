package com.zhuanpan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)  
@Table(name = "magic_config")
public class Magic_Config {
	
	private static final long serialVersionUID=1L;
	private Integer id;
	private String praisefeild;//奖项标示字段
	private String praisename;//奖项名字
	private String min;//最小角度
	private String max;//最大角度		
	private String praisecontent;//奖项内容
	private String praisenumber;//奖项库存次数
	private int chance;//本奖项的概率
	
	public Magic_Config(){
		
	}
	public  Magic_Config(String praisefeild,
						String praisename,
						String min,
						String max,
						String praisecontent,
						String praisenumber,
						int chance){
		this.praisefeild=praisefeild;
		this.praisename=praisename;
		this.min=min;
		this.max=max;
		this.praisecontent=praisecontent;
		this.praisenumber=praisenumber;
		this.chance=chance;
		
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
	@Column(name = "praisefeild")
	public String getPraisefeild() {
		return praisefeild;
	}
	public void setPraisefeild(String praisefeild) {
		this.praisefeild = praisefeild;
	}
	@Column(name = "praisename")
	public String getPraisename() {
		return praisename;
	}
	public void setPraisename(String praisename) {
		this.praisename = praisename;
	}
	@Column(name = "min")
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	@Column(name = "max")
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	@Column(name = "praisecontent")
	public String getPraisecontent() {
		return praisecontent;
	}
	public void setPraisecontent(String praisecontent) {
		this.praisecontent = praisecontent;
	}
	@Column(name = "praisenumber")
	public String getPraisenumber() {
		return praisenumber;
	}
	public void setPraisenumber(String praisenumber) {
		this.praisenumber = praisenumber;
	}
	@Column(name = "chance")
	public int getChance() {
		return chance;
	}
	public void setChance(int chance) {
		this.chance = chance;
	}
	
	
	

}

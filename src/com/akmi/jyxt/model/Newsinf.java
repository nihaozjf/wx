package com.akmi.jyxt.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Newsinf entity. @author MyEclipse Persistence Tools
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)  
@Table(name = "newsinf")
public class Newsinf implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1063201316939237828L;
	private Integer newsid;
	private Teacherinf teacherinf;
	private Newstype newstype;
	private String newstitle;
	private String newscontent;
	private Timestamp newsdate;
	private Timestamp zhidingdate;
	private Short iszhiding;
	private Short ispicnews;
	private String picurl;
	private String linkurl;

	// Constructors

	/** default constructor */
	public Newsinf() {
	}

	/** full constructor */
	public Newsinf(Teacherinf teacherinf, Newstype newstype, String newstitle,
			String newscontent, Timestamp newsdate, Timestamp zhidingdate,
			Short iszhiding, Short ispicnews, String picurl, String linkurl) {
		this.teacherinf = teacherinf;
		this.newstype = newstype;
		this.newstitle = newstitle;
		this.newscontent = newscontent;
		this.newsdate = newsdate;
		this.zhidingdate = zhidingdate;
		this.iszhiding = iszhiding;
		this.ispicnews = ispicnews;
		this.picurl = picurl;
		this.linkurl = linkurl;
	}
	

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "newsid", unique = true, nullable = false)
	public Integer getNewsid() {
		return this.newsid;
	}

	public void setNewsid(Integer newsid) {
		this.newsid = newsid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "newswriter")
	public Teacherinf getTeacherinf() {
		return this.teacherinf;
	}

	public void setTeacherinf(Teacherinf teacherinf) {
		this.teacherinf = teacherinf;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "newstypeid")
	public Newstype getNewstype() {
		return this.newstype;
	}

	public void setNewstype(Newstype newstype) {
		this.newstype = newstype;
	}

	@Column(name = "newstitle")
	public String getNewstitle() {
		return this.newstitle;
	}

	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}

	@Column(name = "newscontent")
	public String getNewscontent() {
		return this.newscontent;
	}

	public void setNewscontent(String newscontent) {
		this.newscontent = newscontent;
	}

	@Column(name = "newsdate", length = 19)
	public Timestamp getNewsdate() {
		return this.newsdate;
	}

	public void setNewsdate(Timestamp newsdate) {
		this.newsdate = newsdate;
	}

	@Column(name = "zhidingdate", length = 19)
	public Timestamp getZhidingdate() {
		return this.zhidingdate;
	}

	public void setZhidingdate(Timestamp zhidingdate) {
		this.zhidingdate = zhidingdate;
	}

	@Column(name = "iszhiding")
	public Short getIszhiding() {
		return this.iszhiding;
	}

	public void setIszhiding(Short iszhiding) {
		this.iszhiding = iszhiding;
	}

	@Column(name = "ispicnews")
	public Short getIspicnews() {
		return this.ispicnews;
	}

	public void setIspicnews(Short ispicnews) {
		this.ispicnews = ispicnews;
	}

	@Column(name = "picurl")
	public String getPicurl() {
		return this.picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	@Column(name = "linkurl")
	public String getLinkurl() {
		return this.linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

}
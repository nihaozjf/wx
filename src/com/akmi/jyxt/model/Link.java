package com.akmi.jyxt.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Link entity. @author MyEclipse Persistence Tools
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)  
@Table(name = "link")
public class Link implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1633635964566786329L;
	private Integer linkinfid;
	private String linkname;
	private String linkurl;
	private String linkimg;
	private Timestamp createdate;
	private Integer orderindex;

	// Constructors

	/** default constructor */
	public Link() {
	}

	/** minimal constructor */
	public Link(String linkname, String linkurl, String linkimg) {
		this.linkname = linkname;
		this.linkurl = linkurl;
		this.linkimg = linkimg;
	}


	/** full constructor */
	public Link(String linkname, String linkurl, String linkimg,
			Integer orderindex) {
		this.linkname = linkname;
		this.linkurl = linkurl;
		this.linkimg = linkimg;
		this.orderindex = orderindex;

	}
	
	/** full constructor */
	public Link(String linkname, String linkurl, String linkimg,
			Integer orderindex,Timestamp createdate) {
		this.linkname = linkname;
		this.linkurl = linkurl;
		this.linkimg = linkimg;
		this.orderindex = orderindex;
		this.createdate=createdate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "linkinfid", unique = true, nullable = false)
	public Integer getLinkinfid() {
		return this.linkinfid;
	}

	public void setLinkinfid(Integer linkinfid) {
		this.linkinfid = linkinfid;
	}

	@Column(name = "linkname", nullable = false, length = 100)
	public String getLinkname() {
		return this.linkname;
	}

	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}

	@Column(name = "linkurl", nullable = false, length = 100)
	public String getLinkurl() {
		return this.linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	@Column(name = "linkimg", nullable = false, length = 100)
	public String getLinkimg() {
		return this.linkimg;
	}

	public void setLinkimg(String linkimg) {
		this.linkimg = linkimg;
	}

	@Column(name = "orderindex")
	public Integer getOrderindex() {
		return this.orderindex;
	}

	public void setOrderindex(Integer orderindex) {
		this.orderindex = orderindex;
	}
	@Column(name = "createdate", length = 16)
	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

}
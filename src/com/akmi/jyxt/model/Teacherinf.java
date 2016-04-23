package com.akmi.jyxt.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Teacherinf entity. @author MyEclipse Persistence Tools
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)  
@Table(name = "teacherinf")
public class Teacherinf implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String teacherid;
	private String teachername;
	private String teacherpwd;
	private Set<Newsinf> newsinfs = new HashSet<Newsinf>(0);

	// Constructors

	/** default constructor */
	public Teacherinf() {
	}

	/** minimal constructor */
	public Teacherinf(String teacherid) {
		this.teacherid = teacherid;
	}

	/** full constructor */
	public Teacherinf(String teacherid, String teachername, String teacherpwd,
			Set<Newsinf> newsinfs) {
		this.teacherid = teacherid;
		this.teachername = teachername;
		this.teacherpwd = teacherpwd;
		this.newsinfs = newsinfs;
	}

	// Property accessors
	@Id
	@Column(name = "teacherid", unique = true, nullable = false, length = 30)
	public String getTeacherid() {
		return this.teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

	@Column(name = "teachername", length = 50)
	public String getTeachername() {
		return this.teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	@Column(name = "teacherpwd", length = 32)
	public String getTeacherpwd() {
		return this.teacherpwd;
	}

	public void setTeacherpwd(String teacherpwd) {
		this.teacherpwd = teacherpwd;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teacherinf")
	public Set<Newsinf> getNewsinfs() {
		return this.newsinfs;
	}

	public void setNewsinfs(Set<Newsinf> newsinfs) {
		this.newsinfs = newsinfs;
	}

}
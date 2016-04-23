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
 * Newstype entity. @author MyEclipse Persistence Tools
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true) 
@Table(name = "newstype")
public class Newstype implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4644998037225407874L;
	private Integer newstypeid;
	
	private Newstype parentnewstype;
	
	private Teacherinf teacherinf;
	private String newstypename;
	private String newstypeename;
	private Timestamp createdate;
	private Integer lmtype;
	
	// Constructors

	/** default constructor */
	public Newstype() {
	}

	/** full constructor */
	public Newstype(Newstype parentnewstype, Teacherinf teacherinf,
			String newstypename, String newstypeename,Timestamp createdate, Integer lmtype
			) {
		this.parentnewstype = parentnewstype;
		this.teacherinf = teacherinf;
		this.newstypename = newstypename;
		this.newstypeename=newstypeename;
		this.createdate = createdate;
		this.lmtype = lmtype;
	}
	
	
	
	public Newstype(Integer newstypeid,Integer lmtype,
			String newstypename) {
		this.newstypename = newstypename;
        this.newstypeid=newstypeid;
        this.lmtype=lmtype;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "newstypeid", unique = true, nullable = false)
	public Integer getNewstypeid() {
		return this.newstypeid;
	}

	public void setNewstypeid(Integer newstypeid) {
		this.newstypeid = newstypeid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentid")
	public Newstype getParentnewstype() {
		return this.parentnewstype;
	}

	public void setParentnewstype(Newstype parentnewstype) {
		this.parentnewstype = parentnewstype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oweruser")
	public Teacherinf getTeacherinf() {
		return this.teacherinf;
	}

	public void setTeacherinf(Teacherinf teacherinf) {
		this.teacherinf = teacherinf;
	}

	@Column(name = "newstypename")
	public String getNewstypename() {
		return this.newstypename;
	}

	public void setNewstypename(String newstypename) {
		this.newstypename = newstypename;
	}
	
	@Column(name = "newstypeename")
	public String getNewstypeename() {
		return this.newstypeename;
	}

	public void setNewstypeename(String newstypeename) {
		this.newstypeename = newstypeename;
	}

	@Column(name = "createdate", length = 19)
	public Timestamp getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	@Column(name = "lmtype")
	public Integer getLmtype() {
		return this.lmtype;
	}

	public void setLmtype(Integer lmtype) {
		this.lmtype = lmtype;
	}
/*
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "newstype")
	public Set<Newstype> getNewstypes() {
		return this.newstypes;
	}

	public void setNewstypes(Set<Newstype> newstypes) {
		this.newstypes = newstypes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "newstype")
	public Set<Newsinf> getNewsinfs() {
		return this.newsinfs;
	}

	public void setNewsinfs(Set<Newsinf> newsinfs) {
		this.newsinfs = newsinfs;
	}
*/
}
package com.akmi.jyxt.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * JyxtLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)  
@Table(name = "userlog")
public class UserLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer logid;
	private String adminid;
	private Timestamp logdate;
	private String ip;
	private Integer logresult;

	// Constructors

	/** default constructor */
	public UserLog() {
	}

	/** full constructor */
	public UserLog(String adminid, Timestamp logdate, String ip,
			Integer logresult) {
		this.adminid = adminid;
		this.logdate = logdate;
		this.ip = ip;
		this.logresult = logresult;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "logid", unique = true, nullable = false)
	public Integer getLogid() {
		return this.logid;
	}

	public void setLogid(Integer logid) {
		this.logid = logid;
	}

	@Column(name = "adminid", nullable = false, length = 20)
	public String getAdminid() {
		return this.adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	@Column(name = "logdate", nullable = false, length = 19)
	public Timestamp getLogdate() {
		return this.logdate;
	}

	public void setLogdate(Timestamp logdate) {
		this.logdate = logdate;
	}

	@Column(name = "ip", nullable = false, length = 15)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "logresult", nullable = false)
	public Integer getLogresult() {
		return this.logresult;
	}

	public void setLogresult(Integer logresult) {
		this.logresult = logresult;
	}

}
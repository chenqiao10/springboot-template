package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 管理账号
 * 
 * @author sunzhu
 *
 */
@Entity
@Table(name = "yl_visit")
public class Visit {

	// 主键id
	@Id
	@GeneratedValue
	@Column(name = "id", length = 10)
	private Integer id;

	// 管理员uuid
	@Column(name = "username", length = 32)
	private String username;
	@Column(name = "password", length = 32)
	private String password;
	/*
	 * @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
	 * CascadeType.REFRESH }, fetch=FetchType.EAGER)
	 * //多的一方维护的列，foreignKey实际为索引值，并不是外兼值
	 * 
	 * @JoinColumn(name = "uId", foreignKey = @ForeignKey(name = "uId", value
	 * =ConstraintMode.CONSTRAINT)) private List<SysRole> roles;
	 */
	  
	  @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	    @JoinColumn(name="user",referencedColumnName = "id")
	  private List<SysRole> roles;
	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Visit [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}

	public Visit() {
		super();
	}

	public Visit(String username, String password, List<SysRole> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

}
package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "yl_role")
public class SysRole {
	@Id
	@GeneratedValue
	@Column(name = "id", length = 10)
    private Integer id;
	@Column(name = "name", length = 32)
    private String name;
	@ManyToOne(cascade=CascadeType.REFRESH,optional=false,fetch=FetchType.EAGER)
	@JoinColumn(name="user",referencedColumnName="id",nullable=false)
    private Visit user;
	 
	public Visit getUser() {
		return user;
	}
	public void setUser(Visit user) {
		this.user = user;
	}
	public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



}
package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Visit;

 

public interface AdminDao {
	public List queryEmpAll();

	public Visit findByUserName(String username);

	void insertAllStudent(List<Visit> list) throws RuntimeException;  

}

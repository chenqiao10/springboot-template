package com.example.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;
import com.example.demo.model.Visit;
@Service
public class VisitServiceImpl implements VisitService{
@Resource 
AdminDao dao;
	public List<Visit> queryEmpAll() {
		// TODO Auto-generated method stub
		return dao.queryEmpAll();
	}
}

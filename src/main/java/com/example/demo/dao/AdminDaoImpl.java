package com.example.demo.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Visit;

@Service
public class AdminDaoImpl implements AdminDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Visit> queryEmpAll() {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("select t from Visit t");

		List<Visit> list = query.getResultList();
		return list;
	}

	@Override
	public Visit findByUserName(String username) {
		Query query = entityManager.createQuery("select t from Visit t where username=?1");
		query.setParameter(1, username);
		List<Visit> list = query.setMaxResults(1).setFirstResult(0).getResultList();
		if (list.size() == 0)
		{
			return null;
		}
		System.out.println(list.toString());
		 
		return list.get(0);
		 

	}
	/*
	 * @Override public void insertAllStudent(List<Visit> list) throws
	 * RuntimeException { if(list != null) { if(list.size()>0) { jdbcTemplate.
	 * batchUpdate("INSERT INTO yl_visit(password,username) values(?,?)", new
	 * BatchPreparedStatementSetter() {
	 * 
	 * @Override public void setValues(PreparedStatement ps, int index) throws
	 * SQLException { ps.setString(1, list.get(index).getPassword());
	 * ps.setString(2, list.get(index).getUsername());
	 * 
	 * }
	 * 
	 * @Override public int getBatchSize() { return list.size(); } }); } } }
	 */

	@Override
	public void insertAllStudent(List<Visit> list) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

}

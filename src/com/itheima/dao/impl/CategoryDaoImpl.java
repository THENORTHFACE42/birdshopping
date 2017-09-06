package com.itheima.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.dao.CategoryDao;
import com.itheima.domain.Category;
import com.itheima.utils.DataSourceUtils;

public class CategoryDaoImpl implements CategoryDao {

	
	/**
	 * 查询所有分类
	 * @throws SQLException 
	 */
	@Override
	public List<Category> findAll() throws Exception {
		// TODO Auto-generated method stub
		
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql="select * from category"; 
		return qr.query(sql, new BeanListHandler<>(Category.class));
	}

	
	
	/**
	 * 添加分类
	 */
	@Override
	public void add(Category c) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into category values(?,?)";
		qr.update(sql,c.getCid(),c.getCname());
		
	}



	/**
	 * 通过id查询分类信息
	 */
	@Override
	public Category getById(String cid) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from category where cid=? limit 1";
		return qr.query(sql, new BeanHandler<>(Category.class),cid);
	}



	/**
	 * 跟新分类
	 */
	@Override
	public void update(Category c) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update category set cname=? where cid=?";
		qr.update(sql,c.getCname(),c.getCid());
	}



	
/**
 * 删除商品分类
 */
	@Override
	public void delete(String cid) throws Exception {
		QueryRunner qr=new QueryRunner();
		String sql="delete from category where cid=?";
		qr.update(DataSourceUtils.getConnection(),sql,cid);
	}

}

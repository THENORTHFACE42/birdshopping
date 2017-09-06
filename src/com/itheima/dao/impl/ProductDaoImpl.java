package com.itheima.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.dao.ProductDao;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.utils.DataSourceUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ProductDaoImpl implements ProductDao{

	
	/**
	 * 查询最新
	 * @throws SQLException 
	 */
	@Override
	public List<Product> findNew() throws SQLException {
		// TODO Auto-generated method stub
		
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product order by pdate limit 9";
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	
	/**
	 * 查询最热门
	 * @throws SQLException 
	 */
	@Override
	public List<Product> findHot() throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where is_hot=1 order by pdate limit 9";
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	
	/**
	 * 通过id查询单个商品
	 */

	@Override
	public Product getById(String pid) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where pid=? limit 1";
		return qr.query(sql, new BeanHandler<Product>(Product.class),pid);
	}





	/**
	 * 查询当前页面商品
	 */
	@Override
	public List<Product> findCurrProduct(int currPage, int pageSize, String cid) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where cid=? limit ?,?";
		return qr.query(sql, new BeanListHandler<Product>(Product.class),cid,(currPage-1)*pageSize,pageSize);
	}

	
	/**
	 * 查询对应商品的总数
	 */

	@Override
	public int getTotalCount(String cid) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select  count(*) from product where cid=?";
		return ((Long)qr.query(sql, new ScalarHandler(),cid)).intValue();
	}

	/**
	 * 跟新商品
	 */

	@Override
	public void updateCid(String cid) throws Exception {
		QueryRunner qr=new QueryRunner();
		String sql="update product set cid=null where cid=?";
		qr.update(DataSourceUtils.getConnection(),sql,cid);
	}


	
	
	/**
	 * 分页查询所有商品
	 */
	
	@Override
	public List<Product> findAll(int currPage, int pageSize) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product limit ?,? ";
		return qr.query(sql, new BeanListHandler<Product>(Product.class),(currPage-1)*pageSize,pageSize);
	}


	/**
	 * 查询所有商品的总数量
	 */
	@Override
	public int getAllProductCount() throws Exception {
		
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count(*) from product";
		return ((Long)qr.query(sql, new ScalarHandler())).intValue();
	}

	
	/**
	 * 添加商品
	 */

	@Override
	public void add(Product product) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into product values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql,product.getPid(),product.getPname(),product.getMarket_price()
				,product.getShop_price(),product.getPimage(),product.getPdate(),
				product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCategory().getCid());
	}

}

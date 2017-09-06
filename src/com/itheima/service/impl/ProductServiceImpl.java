package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.itheima.dao.ProductDao;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;

public class ProductServiceImpl implements ProductService {

	
	
	/**
	 * 查询最新
	 * @throws SQLException 
	 */
	@Override
	public List<Product> findNew() throws SQLException {
		// TODO Auto-generated method stub
		
		ProductDao pdao=(ProductDao) BeanFactory.getBean("ProductDao");
		return pdao.findNew();
	}

	
	/**
	 * 查询最新
	 * @throws SQLException 
	 */
	@Override
	public List<Product> findHot() throws SQLException {
		// TODO Auto-generated method stub
		
		ProductDao pdao=(ProductDao) BeanFactory.getBean("ProductDao");
		return pdao.findHot();
	}

	/**
	 * 通过单个id查找商品
	 */

	@Override
	public Product getById(String pid) throws SQLException {
		ProductDao pdao=(ProductDao) BeanFactory.getBean("ProductDao");
		return pdao.getById(pid);
	}


	
	/**
	 * 根据类别分页查询商品
	 * @throws SQLException 
	 */
	@Override
	public PageBean<Product> findByPage(int currPage, int pageSize, String cid) throws SQLException {
		
		ProductDao pdao=(ProductDao) BeanFactory.getBean("ProductDao");
		
		//当前页数据
		List<Product> list=pdao.findCurrProduct(currPage,pageSize,cid);
		
		//获取相应商品的总数
		int totalCount=pdao.getTotalCount(cid);
		
		
		return new 	PageBean<Product>(list, currPage, pageSize, totalCount);
	}


	/**
	 * 分页查询所有商品
	 */
	@Override
	public PageBean<Product> findAll(int currPage, int pageSize) throws Exception {
		ProductDao pdao=(ProductDao) BeanFactory.getBean("ProductDao");
		
		List<Product> list=pdao.findAll(currPage,pageSize);
		
		//获取相应商品的总数
		int totalCount=pdao.getAllProductCount();
		
		return new 	PageBean<Product>(list, currPage, pageSize, totalCount);

	}

	
	/**
	 * 添加商品
	 */

	@Override
	public void add(Product product) throws Exception {
		
		ProductDao pdao=(ProductDao) BeanFactory.getBean("ProductDao");
	     pdao.add(product);
		
	}

	



}

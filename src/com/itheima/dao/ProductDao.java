package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.domain.PageBean;
import com.itheima.domain.Product;

public interface ProductDao {

	List<Product> findNew() throws SQLException;

	List<Product> findHot() throws SQLException;

	Product getById(String pid)throws SQLException;

	List<Product> findCurrProduct(int currPage, int pageSize, String cid) throws SQLException;

	int getTotalCount(String cid) throws SQLException;

	void updateCid(String cid) throws Exception;

	List<Product> findAll(int currPage, int pageSize) throws Exception;

	int getAllProductCount()throws Exception;

	void add(Product product) throws Exception;


}

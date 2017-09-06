package com.itheima.service;

import java.sql.SQLException;
import java.util.List;

import com.itheima.domain.PageBean;
import com.itheima.domain.Product;

public interface ProductService {

	List<Product> findNew() throws SQLException;

	List<Product> findHot() throws SQLException;

	Product getById(String pid) throws SQLException;

	PageBean<Product> findByPage(int currPage, int pageSize, String cid) throws SQLException;

	PageBean<Product> findAll(int currPage, int pageSize) throws Exception;

	void add(Product product) throws Exception;


}

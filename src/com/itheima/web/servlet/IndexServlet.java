package com.itheima.web.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Category;
import com.itheima.domain.Product;
import com.itheima.service.CategoryService;
import com.itheima.service.ProductService;
import com.itheima.service.impl.CategoryServiceImpl;
import com.itheima.service.impl.ProductServiceImpl;

/**
 * 和首页相关的servlet
 */
public class IndexServlet extends BaseServlet {
	public String index(HttpServletRequest request, HttpServletResponse response) {
		
		
		//去数据库查询最新商品和热门商品 将它们放入request请求域中 请求转发
		ProductService ps=new ProductServiceImpl();
		
		//最新商品
		List<Product> newList=null;
		try {
			newList = ps.findNew();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//热门商品
		List<Product> hotList=null;
		try {
			hotList = ps.findHot();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//将两个list放入request域中
		request.setAttribute("nList", newList);
		request.setAttribute("hList", hotList);
		
		
		return "/jsp/index.jsp";
	}

}

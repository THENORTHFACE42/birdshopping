package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Category;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.CategoryService;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;



/**
 * 后台商品管理
 * @author jenking
 *
 */
public class AdminProduct extends BaseServlet {

	/**
	 * 添加商品
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int currPage=Integer.parseInt(request.getParameter("currPage"));
		int pageSize=6;
		
		
		
		//调用service 查询返回一个list
		ProductService ps=(ProductService) BeanFactory.getBean("ProductService");
		PageBean<Product> bean=null;
		
		try {
			bean = (PageBean<Product>) ps.findAll(currPage,pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("pb", bean);
		
		//放入域中请求转发
	
		return "/admin/product/list.jsp";
	}
	
	//跳转到修改
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		//查询所有的份分类返回一个list
		CategoryService cs=(CategoryService) BeanFactory.getBean("CategoryService");
		List<Category> clist=null;
		try {
			clist=cs.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//放入域名，请求转发
		request.setAttribute("clist", clist);
		return "/admin/product/add.jsp";
	}

}

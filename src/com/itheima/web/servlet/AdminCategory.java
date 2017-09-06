package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.UUIDUtils;

public class AdminCategory extends BaseServlet {

	/**
	 * 查找所有分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//调用CategoryService
		CategoryService cs=(CategoryService) BeanFactory.getBean("CategoryService");
		
		
		List<Category> list=null;
		try {
			list=cs.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//返回list,加入域中
		request.setAttribute("list", list);
		return "/admin/category/list.jsp";
	}
	
	/**
	 * 添加分类  跳转到添加分类的
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "admin/category/add.jsp";
	}
	
	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收cname
		String cname=request.getParameter("cname");
		
		//封装category
		Category c=new Category();
		c.setCid(UUIDUtils.getId());
		c.setCname(cname);
		
		//调用service
		CategoryService cs=(CategoryService) BeanFactory.getBean("CategoryService");
		try {
			cs.add(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		
		return null;
	}
	
	/**
	 * 通过id获取分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid=request.getParameter("cid");
		
		//调用service层完成查询操作，返回值：category
		CategoryService cs=(CategoryService) BeanFactory.getBean("CategoryService");
		Category c=null;
		try {
			c=cs.getById(cid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//category放入域中请求转发
		
		request.setAttribute("bean", c);
		return "/admin/category/edit.jsp";
	}
	/**
	 * 更新分类信息方法
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	
		Category c=new Category();
		c.setCid(request.getParameter("cid"));
		c.setCname(request.getParameter("cname"));
		
		//调用service完成跟新操作
		CategoryService cs=(CategoryService) BeanFactory.getBean("CategoryService");
		try {
			cs.update(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		return null; 
	}
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid=request.getParameter("cid");
		
		//调用service
		CategoryService cs=(CategoryService) BeanFactory.getBean("CategoryService");
		try {
			cs.delete(cid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//重定向
		response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		
		
		
		return null;
	}
	

}

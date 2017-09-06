package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.JsonUtils;

public class CategoryServlet extends BaseServlet {

	
	/**
	 * 查询所有的分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		//去数据库中查询最新商品和热门商品  将他们放入request域中 请求转发
		
		//1调用categoryservice查询所有的分类 返回值list
		CategoryService cs=(CategoryService) BeanFactory.getBean("CategoryService");
		List<Category> clist=null;
		try {
				clist = cs.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}	
				
	    
		//2将返回值转换为json格式返回到页面上
		String json=JsonUtils.list2json(clist);
		
		//3写回
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(json);
		return null;
	}

	

}

package com.itheima.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.service.impl.ProductServiceImpl;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.CookUtils;

public class ProductServlet extends BaseServlet {

	/**
	 * 通过id查找商品并添加浏览记录
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//获取商品id
		String pid=request.getParameter("pid");
		
		//调用service
		ProductService ps=(ProductService) BeanFactory.getBean("ProductService");
		Product p=null;
		try {
			p = ps.getById(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		//Cookies部分  浏览记录
		//获取指定的cookie idAndUrl
		String idAndUrl="";
		Cookie c=CookUtils.getCookieByName("idAndUrl", request.getCookies());
		
		//判断cookie是否为空
		if(c==null)
		{
			//若cookie为空,将商品id和商品图片地址放入idAndUrl中
			idAndUrl=p.getPid()+"-"+p.getPimage();
		}
		else
		{				
			//若cookie不为空，继续判断idAndUrl是否包含商品
			idAndUrl=c.getValue();
			String[] arr=idAndUrl.split("&");
			
			//将数组转换为集合
			List<String> asList=Arrays.asList(arr);
			//将aList放入一个新的list中
			LinkedList<String> list=new LinkedList<String>(asList);
			
			
			if(list.contains(pid+"-"+p.getPimage()))
			{
				list.remove(pid+"-"+p.getPimage());
				list.addFirst(pid+"-"+p.getPimage());
			}
			else
			{
				//如果idAndUrl中不包含pid+"-"+p.pimage,继续判断长度是否大于2
				if(list.size()>5)
				{
					list.removeLast();
					list.addFirst(pid+"-"+p.getPimage());
				}
				else
				{
					list.add(pid+"-"+p.getPimage());
				}
			}
			
			//将list转为字符串
			
			idAndUrl="";
			for(String s:list)
			{
				idAndUrl+=(s+"&");
			}
			idAndUrl=idAndUrl.substring(0,idAndUrl.length()-1);
			
			//将list转为字符串
		}
		
	
		c=new Cookie("idAndUrl", idAndUrl);
		
		//设置访问路径
		c.setPath(request.getContextPath()+"/");
		//设置存活时间
		c.setMaxAge(3600);
		
		
		//写回浏览器
		response.addCookie(c);
	
		//将结果放入request中请求转发
		request.setAttribute("bean", p);
		return "jsp/product_info.jsp";
	}
	
	/**
	 * 分页查询商品
	 */
	
	public String findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//获取商品类别id和当前页码
		String cid=request.getParameter("cid");
		int currPage=Integer.parseInt(request.getParameter("currPage"));
		
		//设置页面大小
		int pageSize=12;
		
		
		//调用service返回pagebean
		ProductService ps=(ProductService) BeanFactory.getBean("ProductService");
		PageBean<Product> bean=null;
		try {
			bean = ps.findByPage(currPage,pageSize,cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//放入请求域中
		request.setAttribute("pb", bean);
		
	
		return "jsp/product_list.jsp";
	}
	
	
	/**
	 * 清空浏览记录，cookies置为空
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String clearHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		//获取当前页码
		String currPage=request.getParameter("currPage");
		String cid=request.getParameter("cid");
		
		Cookie c=new Cookie("idAndUrl","");
		c.setPath(request.getContextPath()+"/");
		
		//设置时间
		c.setMaxAge(0);
		response.addCookie(c);

		//转发到分页查询的页面中
		String path=request.getContextPath()+"/product?method=findByPage&currPage="+currPage+"&cid="+cid;
		response.sendRedirect(path);
		
     	return null;
	} 	
	

}

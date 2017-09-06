package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Order;
import com.itheima.service.OrderService;
import com.itheima.utils.BeanFactory;



/**
 * 后台订单模块
 * @author jenking
 *
 */
public class AdminOrder extends BaseServlet {

	
	public String findAllByState(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//接受state
		String state=request.getParameter("state");
		
		//调用service
		List<Order> list=null;
		OrderService os=(OrderService) BeanFactory.getBean("OrderService");
		list = os.findAllByState(state);
	
		
		//放入域中请求转发
		request.setAttribute("list", list);
		return "/admin/order/list.jsp";
		
		
	}

	
	

}

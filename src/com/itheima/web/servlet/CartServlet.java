package com.itheima.web.servlet;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Cart;
import com.itheima.domain.CartItem;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;


/**
 * 购物车模块
 * @author jenking
 *
 */
public class CartServlet extends BaseServlet{


	public Cart getCart(HttpServletRequest request)
	{
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		
		if(cart==null)
		{
			cart=new Cart();
			
			request.getSession().setAttribute("cart", cart);
		}
		
		return cart;
		
	}
	
	
	
	
   /**
    * 添加到购物车
    * @param request
    * @param response
    * @return
    * @throws ServletException
    * @throws IOException
    */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		
		//获取pid和数量
		String pid=request.getParameter("pid");
		int count=Integer.parseInt(request.getParameter("count"));
		
		//查询返回product(通过pid获取一个商品)
		ProductService ps=(ProductService) BeanFactory.getBean("ProductService");
		Product product=ps.getById(pid);
		
		
		//组装成CartItem
		CartItem cartItem=new CartItem(product,count);
		
		//添加到购物车
		Cart cart=getCart(request);
		cart.add2Cart(cartItem);
		
		//重定向
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
	    return null;
		
	}
	
	/**
	 * 删除购物车的购物项
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws Exception
	 */
	public String remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		
		//获取商品的pid
		String pid=request.getParameter("pid");
		
		//调用购物车的remove方法
		getCart(request).removeFromCart(pid);
		
		//重定向
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
	    return null;
		
	}
	
	
	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws Exception
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		
		//获取购物车并清空
		getCart(request).clearCart();
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
	    return null;
		
	}
	

}

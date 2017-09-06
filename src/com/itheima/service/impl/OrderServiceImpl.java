package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.itheima.dao.OrderDao;
import com.itheima.domain.Order;
import com.itheima.domain.OrderItem;
import com.itheima.domain.PageBean;
import com.itheima.domain.User;
import com.itheima.service.OrderService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService {

	@Override
	public void add(Order order) throws Exception {
		
		try {

			//1开启事务
			DataSourceUtils.startTransaction();
			
			OrderDao od=(OrderDao) BeanFactory.getBean("OrderDao");
			//2向orders表中添加数据
			od.add(order);
			
//			int i=1/0;
			//向orderitems中添加数据
			for (OrderItem oi : order.getItems()) {
				od.addItem(oi);
				 
			}

			//事务处理
			DataSourceUtils.commitAndClose();
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
		
		
		
		
		
	
		
	}
	
	/**
	 * 分页查询订单
	 */

	@Override
	public PageBean<Order> findAllBypage(int currPage, int pageSize, User user) throws Exception {
		// 查询当页数据
		OrderDao od=(OrderDao) BeanFactory.getBean("OrderDao");
		
		List<Order> list=od.findAllByPage(currPage,pageSize,user.getUid());
		
		//查询总条数
		int totalCount=od.getTotalCount(user.getUid());
		
		return new PageBean<Order>(list, currPage, pageSize, totalCount);
	}

	
	/**
	 * 通过订单号oid查找订单详情 返回Orderbean
	 */
	@Override
	public Order getById(String oid) throws Exception {
		OrderDao od=(OrderDao) BeanFactory.getBean("OrderDao");
		return od.getById(oid);
	}

	
	/**
	 * 更新order
	 */
	@Override
	public void updateOrder(Order order) throws Exception {
		OrderDao od=(OrderDao) BeanFactory.getBean("OrderDao");
		od.update(order);	
		
	}
	
	/**
	 * 根据状态查询订单
	 */

	@Override
	public List<Order> findAllByState(String state) throws Exception {
		OrderDao odao=(OrderDao) BeanFactory.getBean("OrderDao");

		return odao.findAllByState(state);
	}

}

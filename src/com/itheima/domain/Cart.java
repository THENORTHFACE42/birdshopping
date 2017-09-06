package com.itheima.domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

	//存放购物车项的集合 key:商品id cartItem：购物车项 使用map集合便于删除单个购物车项
	private Map<String,CartItem> map=new LinkedHashMap<>();
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	private double total=0.0;//总金额
	
	
	
	
	/**
	 * 获取所有的购物车项
	 * @return 
	 */
	public Collection<CartItem> getItems()
	{
		return map.values();
	}
	
	
	
	
	
	
	
	
	/**
	 * 添加购物车
	 * @param item购物车项
	 */
	public void add2Cart(CartItem item)
	{
		//判断购物车中有无商品
		String pid=item.getProduct().getPid();
		if(map.containsKey(pid))
		{
			CartItem oItem=map.get(pid);
			oItem.setCount(oItem.getCount()+item.getCount());
		}
		else
		{
			//没有商品
			map.put(pid, item);
		}
		
		//添加之后修改金额
		total+=item.getSubtotal();
	}
	/**
	 * 从购物车中删除指定的购物项
	 * @param pid 商品id
	 */
	public void removeFromCart(String pid)
	{
		//从集合中移除
		CartItem item=map.remove(pid);
		//修改金额
		total-=item.getSubtotal();
	}
	/**
	 * 清空购物车
	 */
	public void clearCart()
	{
		map.clear();
		total=0.0;
	}
	
	
	
}

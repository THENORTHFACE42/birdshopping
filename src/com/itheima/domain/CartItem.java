package com.itheima.domain;




/**
 * 购物车项
 * @author jenking
 *
 */
public class CartItem {
	
	private Product product; //商品对象
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return product.getShop_price()*count;
	}

	private Integer count;//购买数量
	private Double subtotal=0.0; //小计
	public CartItem() {
		
	}
	public CartItem(Product product, Integer count) {
		this.product = product;
		this.count = count;
	}
	
	

}

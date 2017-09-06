package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.List;


import com.itheima.dao.CategoryDao;
import com.itheima.dao.ProductDao;
import com.itheima.dao.impl.CategoryDaoImpl;
import com.itheima.domain.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.DataSourceUtils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CategoryServiceImpl implements CategoryService {

	
	
	/**
	 * 查询所有的分类
	 * @throws Exception 
	 */
	@Override
	public List<Category> findAll() throws Exception{
		// TODO Auto-generated method stub
		
		
		//1创建缓存管理器
		CacheManager cm=CacheManager.create(CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		
		//2获取指定的缓存
		Cache cache=cm.getCache("categoryCache");
		
		//3通过缓存获取数据,将cache看成是一个map
		Element element=cache.get("clist");
		
		List<Category> list=null;
		
		//4判断数据
		if(element==null)
		{
			//从数据库中获取
			CategoryDao cd=(CategoryDao) BeanFactory.getBean("CategoryDao");
			list= cd.findAll();
			
			//加入缓存中
			cache.put(new Element("clist",list));
			
			System.out.println("缓存中没有数据，去数据库中获取");
		}
		else
		{
			//直接返回
			list=(List<Category>) element.getObjectValue();
			System.out.println("缓存中有数据");
		}
		return list;
		
	}
	
	/**
	 * 添加分类
	 */

	@Override
	public void add(Category c) throws Exception {
		CategoryDao cdao=(CategoryDao) BeanFactory.getBean("CategoryDao");
		cdao.add(c);
		
		//更新缓存
		//创建缓存管理器
		CacheManager cm=CacheManager.create(CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		//获取指定缓存
		Cache cache=cm.getCache("categoryCache");
		
		//清空
		cache.remove("clist");
		
		
	}
	
	/**
	 * 通过id查询分类信息
	 */

	@Override
	public Category getById(String cid) throws Exception {
		CategoryDao cdao=(CategoryDao) BeanFactory.getBean("CategoryDao");
		
		return cdao.getById(cid);
	}

	
	/**
	 * 跟新分类信息
	 */
	@Override
	public void update(Category c) throws Exception {
		CategoryDao cdao=(CategoryDao) BeanFactory.getBean("CategoryDao");
		cdao.update(c);
		
		//清空缓存
		CacheManager cm=CacheManager.create(CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		Cache cache=cm.getCache("categoryCache");
		cache.remove("clist");
			
		
	}

	/**
	 * 删除分类
	 * @throws SQLException 
	 */
	@Override
	public void delete(String cid) throws Exception  {
	
		try {
			//开启事务
			DataSourceUtils.startTransaction();
			
			//跟新商品信息
			ProductDao pd=(ProductDao) BeanFactory.getBean("ProductDao");
			pd.updateCid(cid);
			
			//删除分类
			
			CategoryDao cdao=(CategoryDao) BeanFactory.getBean("CategoryDao");
			cdao.delete(cid);
			
			//事务控制
			DataSourceUtils.commitAndClose();
			
			//清空缓存
			CacheManager cm=CacheManager.create(CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
			Cache cache=cm.getCache("categoryCache");
			cache.remove("clist");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
		
		
		
		
		
	}
}

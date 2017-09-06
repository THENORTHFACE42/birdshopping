package com.itheima.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class BeanFactory {

	
	public  static Object getBean(String id) 
	{
	
		try {
			//获取document对象
			Document doc=new SAXReader().read(BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml"));
			//获取指定的bean对象
			Element ele= (Element) doc.selectSingleNode("//bean[@id='"+id+"']");
			
			//获取bean对象的class属性
			String value=ele.attributeValue("class");
			
			//反射
			return Class.forName(value).newInstance();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		System.out.println(getBean("OrderDao"));
		System.out.println(getBean("OrderService"));
		
		
	}
}

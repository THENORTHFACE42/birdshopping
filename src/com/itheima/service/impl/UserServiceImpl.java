package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.MailUtils;

public class UserServiceImpl implements UserService{

	/**
	 * 用户注册
	 */
	@Override
	public void regist(User user)  throws Exception{
		UserDao dao=new UserDaoImpl();
		dao.add(user);
		//发送邮件
		
		//email:收件人地址
		//emailMsg:邮件的内容
		String emailMsg="欢迎您注册成我们的一员,<a href='http://localhost:8888/store/user?method=active&code="+user.getCode()+"'>点此激活</a>";
		
//		发送邮件
		MailUtils.sendMail(user.getEmail(), emailMsg);
	}

	/**
	 * 用户激活
	 * @throws Exception 
	 */
	@Override
	public User active(String code) throws Exception {
		UserDao dao=(UserDao) BeanFactory.getBean("UserDao");
		//1.通过code获取一个用户
		User user=dao.getByCode(code);
		
		//2.判断用户是否为空
		if(user==null){
			return null;
		}
		
		//3.修改用户状态
		//将用户的状态设置为1
		user.setState(1);
		dao.update(user);
		
		return user;
	}

	/**
	 * 用户登录
	 */
	@Override
	public User login(String username, String password) throws Exception {
		UserDao dao=new UserDaoImpl();
		return dao.getByUsernameAndPwd(username,password);
	}

}

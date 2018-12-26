package com.bodao.ehrms.DAO;

import java.util.List;

import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.entity.User;


public interface AdminDAO{
	
	public User findUserByNameAndPwd(String name,String pwd,int ytpe);
	
  
	public int add(User user);

	

	

	
	
	

}

package com.bodao.ehrms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.bodao.ehrms.DAO.AdminDAO;
import com.bodao.ehrms.DAO.CarDAO;
import com.bodao.ehrms.DAO.RecordDAO;
import com.bodao.ehrms.DAO.Impl.AdminDAOlmpl;
import com.bodao.ehrms.DAO.Impl.CarDAOImpl;
import com.bodao.ehrms.DAO.Impl.RecordDAOImpl;
import com.bodao.ehrms.entity.Brand;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.entity.Category;
import com.bodao.ehrms.entity.Record;
import com.bodao.ehrms.entity.User;
import com.bodao.ehrms.util.URLPathUtil;


public class AdminService{
	public User login(String name,String pwd,int type) {
		//到数据库查询
		AdminDAO adminDAO =new AdminDAOlmpl();
		//调用根据用户和密码查询管理员信息返回一个admin对象
		User admin =adminDAO.findUserByNameAndPwd(name, pwd,type);
	    return admin;
	}
	/**
	 * 查询汽车信息
	 * @return
	 */
	public List<Car> select(){
		CarDAO cardao=new CarDAOImpl();
		List<Car> row=cardao.findAll();
		return row;
	}

	/**
	 * 修改汽车信息
	 * @param rent
	 * @param useable
	 * @param id
	 * @return
	 */
	public  int updata(double rent,int useable,int id) {
		CarDAO car=new CarDAOImpl();
        int mp1 =car.updateCar(rent, useable, id);
        return mp1;
	}
	/**
	 * 添加汽车
	 * @param car
	 * @return
	 */
	public boolean addCar(Car car) {
		CarDAO dao=new CarDAOImpl();
		int row =dao.addCar(car);	
		if (row>=1) {
			return true;
		}
		return false;
	}
	/**
	 * 查询汽车租赁记录
	 * @return
	 */
public List<Record> selectrecord () {
	RecordDAO cc=new RecordDAOImpl();
	List<Record> lp=cc.findAll();
	return lp;
}
/**
 * 根据汽车ID查询
 * @param car_id
 * @return
 */
public Car selectCarId(int car_id) {
	CarDAO cc=new CarDAOImpl();
	Car hg=cc.findCarByCar_id(car_id);
	return hg;
}
/**
 * 查询所有品牌
 * @return
 */
public List<Brand> selescBrandAll(){
	CarDAO cc=new CarDAOImpl();
	List<Brand> jk=cc.findBrandAll();
	return jk;
}
/**
 * 查询所有类别
 * @return
 */
public List<Category> selectCategoryAll(){
	CarDAO cc=new CarDAOImpl();
	List<Category> ok=cc.findCategoryAll();
	System.out.println(ok);
	return ok;
	}
}
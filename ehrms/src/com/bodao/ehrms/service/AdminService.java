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
		//�����ݿ��ѯ
		AdminDAO adminDAO =new AdminDAOlmpl();
		//���ø����û��������ѯ����Ա��Ϣ����һ��admin����
		User admin =adminDAO.findUserByNameAndPwd(name, pwd,type);
	    return admin;
	}
	/**
	 * ��ѯ������Ϣ
	 * @return
	 */
	public List<Car> select(){
		CarDAO cardao=new CarDAOImpl();
		List<Car> row=cardao.findAll();
		return row;
	}

	/**
	 * �޸�������Ϣ
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
	 * �������
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
	 * ��ѯ�������޼�¼
	 * @return
	 */
public List<Record> selectrecord () {
	RecordDAO cc=new RecordDAOImpl();
	List<Record> lp=cc.findAll();
	return lp;
}
/**
 * ��������ID��ѯ
 * @param car_id
 * @return
 */
public Car selectCarId(int car_id) {
	CarDAO cc=new CarDAOImpl();
	Car hg=cc.findCarByCar_id(car_id);
	return hg;
}
/**
 * ��ѯ����Ʒ��
 * @return
 */
public List<Brand> selescBrandAll(){
	CarDAO cc=new CarDAOImpl();
	List<Brand> jk=cc.findBrandAll();
	return jk;
}
/**
 * ��ѯ�������
 * @return
 */
public List<Category> selectCategoryAll(){
	CarDAO cc=new CarDAOImpl();
	List<Category> ok=cc.findCategoryAll();
	System.out.println(ok);
	return ok;
	}
}
package com.bodao.ehrms.service;

import java.util.List;

import com.bodao.ehrms.DAO.AdminDAO;
import com.bodao.ehrms.DAO.CarDAO;
import com.bodao.ehrms.DAO.RecordDAO;
import com.bodao.ehrms.DAO.Impl.AdminDAOlmpl;
import com.bodao.ehrms.DAO.Impl.CarDAOImpl;
import com.bodao.ehrms.DAO.Impl.RecordDAOImpl;
import com.bodao.ehrms.entity.User;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.entity.Record;

public class UserService{
		public User login(String name,String pwd,int type) {
			//�����ݿ��ѯ
			AdminDAO userDAO =new AdminDAOlmpl();
			//���ø����û��������ѯ����һ��user����
			User user =userDAO.findUserByNameAndPwd(name, pwd,type);	
			return user;
		}
		/**
		 * ע��
		 * @param pwd
		 * @param email
		 * @param uid
		 * @return
		 */
		public boolean register(User user) {
			AdminDAO dao =new AdminDAOlmpl();
			int row =dao.add(user);
			if(row>=1) {
				return true;
			}
			return false;
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
		 * ���ݼ۸��ѯ������Ϣ
		 * @param price
		 * @return
		 */
		public List<Car> selsetprice(double rent){
			
			CarDAO cardao=new CarDAOImpl();
			List<Car> mo=cardao.findCarByPrice(rent);
			return mo;
		
		}
		/**
		 * ��������Ų�ѯ
		 * @param category_id
		 * @return
		 */
		public  List<Car>  selectcategoryid(int category_id){
			CarDAO cardao=new CarDAOImpl();
			List<Car> mo1=cardao.findCarByCategory_id(category_id);
			return mo1;
		}
		
		/**
		 * ����Ʒ�Ʊ��
		 * @param brand_id
		 * @return
		 */
		public List<Car> selectbrandid(int brand_id) {
			CarDAO cardao=new CarDAOImpl();
			List<Car> mo2=cardao.findCarByBrand_id(brand_id);
			return mo2;
			
		}
		/**
		 * ����
		 * @param user_id
		 * @param car_id
		 * @return
		 */
		public Record returncar( int user_id, int car_id ) {			
			CarDAO cardao=new CarDAOImpl();
			RecordDAO record=new RecordDAOImpl();
			Car mo6=cardao.findCarByCar_id(car_id);
			if (mo6.getStatu()==0) {
				return null;
			}else{
			double rent = mo6.getRent();
			int key=record.updaterecord(rent, user_id, car_id);
			int mo7=cardao.updateCar1(0, car_id);
			Record mo8=record.findRecord(user_id, car_id);		
			return mo8;
			}
		}
		
		/**
		 * �⳵
		 * @param user_id
		 * @param car_id
		 * @return
		 * ��ѯ���Ƿ����
		 * ���������ޱ�������ݣ��û�id����id�����µ�����ֵ
		 * ���³���״̬
		 * ��ѯ�⳵��¼
		 * 
		 */
		public Record rentcar(int user_id, int car_id) {
			CarDAO cardao=new CarDAOImpl();
			RecordDAO record=new RecordDAOImpl();
			Car mo6=cardao.findCarByCar_id(car_id);
			int status = mo6.getStatu();
			if(status==1) {
				return null;
			}
			int key=record.addrecord(user_id, car_id);
			int mo7=cardao.updateCar1(1, car_id);
			Record mo8=record.select(key);
			return mo8;
		}
		/**
		 
		 * �����û�ID��ѯ�⳵��¼
		 * @param recordeser_id
		 * @return
		 */
		public List<Record> selescrecorduserid(int user_id){
			RecordDAO record=new RecordDAOImpl();
			List<Record> mo3=record.findRecordByRecordUser_id(user_id);
			return mo3;
		}
}
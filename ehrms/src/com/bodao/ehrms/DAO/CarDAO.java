package com.bodao.ehrms.DAO;

import java.util.List;

import com.bodao.ehrms.entity.Brand;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.entity.Category;


	
public interface CarDAO {
	/**
	 * ���
	 * @param car
	 * @return 
	 * @return
	 */
	public  int addCar(Car car);

	/**
	 * ��ѯ������������
	 * @return
	 */
	public List<Car> findAll();
	/**
	 * ����id��ѯ������Ϣ
	 * @param id
	 * @return
	 */
	public Car findCarByCar_id(int car_id);
	/**
	 * ���ݰ��ռ۸����򡢽����ѯ����
	 * @param id
	 * @return
	 */
	public List<Car> findCarByPrice(double rent);
	/**
	 * ��������Ų�ѯ������Ϣ
	 * @param category_id
	 * @return
	 */
	public List<Car> findCarByCategory_id(int category_id);
	/**
	 * ����Ʒ�Ʊ�Ų�ѯ������Ϣ
	 * @param id
	 * @return
	 */
	public List<Car> findCarByBrand_id(int brand_id);
	/**
	 * ��ѯƷ�Ʊ�
	 * @return
	 */
	public List<Brand> findBrandAll();

	/**
	 * ��ѯ����
	 * @return
	 */
	public List<Category> findCategoryAll();

	/**
	 * �޸�������Ϣ
	 * @param rent
	 * @param useable
	 * @param id
	 * @return
	 */
	public int updateCar(double rent, int useable, int id);
	/**
	 * �޸�����״̬
	 * @param useable
	 * @param id
	 * @return
	 */
	public int  updateCar1(int useable,int id);
	




	


	
	
}
package com.bodao.ehrms.DAO;

import java.util.List;

import com.bodao.ehrms.entity.Brand;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.entity.Category;


	
public interface CarDAO {
	/**
	 * 添加
	 * @param car
	 * @return 
	 * @return
	 */
	public  int addCar(Car car);

	/**
	 * 查询所有汽车数据
	 * @return
	 */
	public List<Car> findAll();
	/**
	 * 根据id查询汽车信息
	 * @param id
	 * @return
	 */
	public Car findCarByCar_id(int car_id);
	/**
	 * 根据按照价格升序、降序查询汽车
	 * @param id
	 * @return
	 */
	public List<Car> findCarByPrice(double rent);
	/**
	 * 根据类别编号查询汽车信息
	 * @param category_id
	 * @return
	 */
	public List<Car> findCarByCategory_id(int category_id);
	/**
	 * 根据品牌编号查询汽车信息
	 * @param id
	 * @return
	 */
	public List<Car> findCarByBrand_id(int brand_id);
	/**
	 * 查询品牌表
	 * @return
	 */
	public List<Brand> findBrandAll();

	/**
	 * 查询类别表
	 * @return
	 */
	public List<Category> findCategoryAll();

	/**
	 * 修改汽车信息
	 * @param rent
	 * @param useable
	 * @param id
	 * @return
	 */
	public int updateCar(double rent, int useable, int id);
	/**
	 * 修改汽车状态
	 * @param useable
	 * @param id
	 * @return
	 */
	public int  updateCar1(int useable,int id);
	




	


	
	
}
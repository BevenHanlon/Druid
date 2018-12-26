package com.bodao.ehrms.DAO.Impl;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bodao.ehrms.DAO.CarDAO;
import com.bodao.ehrms.entity.Brand;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.entity.Category;
import com.bodao.ehrms.util.URLPathUtil;


public class CarDAOImpl implements CarDAO {
	
	/**
	 * 添加汽车信息
	 */
	@Override
	public  int addCar(Car car) {
			Car cars = new Car();
			Connection con = URLPathUtil.getCon();
			String sql = "INSERT INTO t_car(car_number,brand_id,model,color,category_id,t_comments,price,rent,statu,useable)  VALUES(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = null;
			int row = 0;
			try{
			ps = con.prepareStatement(sql);
			ps.setString(1,car.getCar_number());
			ps.setInt(2 ,car.getBrand().getId());
			ps.setString(3,car.getModel());
			ps.setString(4,car.getColor());
			ps.setInt(5, car.getCategory().getId());
			ps.setString(6,car.getT_comments());
			ps.setDouble(7, car.getPrice());
			ps.setDouble(8, car.getRent());
			ps.setInt(9, car.getStatu());
			ps.setInt(10, car.getUseable());		
			row = ps.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				URLPathUtil.close(con,ps,null);
			}
		return row;
	}
	/**
	 * 修改汽车信息
	 */
	@Override
	public int updateCar(double rent, int useable,int id) {
		int row=0;
		Connection con = URLPathUtil.getCon();
		String sql="UPDATE t_car SET rent=?,useable=? WHERE id=?";
		PreparedStatement ps=null;
		try {
			ps=con.prepareStatement(sql);
			ps.setDouble(1,rent);
			ps.setInt(2,useable);
			ps.setInt(3, id);
			 row = ps.executeUpdate();
			System.out.println(row+"记录修改成功！");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			URLPathUtil.close(con, ps, null);
		}
		return row;
	}
	
	/**
	 * 查询全部汽车
	 */
	@Override
	public List<Car> findAll() {
		List<Car> cars=new ArrayList<Car>();
		Connection con = URLPathUtil.getCon();
		String sql = "SELECT * FROM t_car c ,t_brand b ,t_category ca WHERE c.brand_id=b.id AND c.category_id=ca.id";
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Car car=new Car();
				car.setId(rs.getInt("c.id"));
				car.setCar_number(rs.getString("c.car_number"));
				car.setModel(rs.getString("c.model"));
				car.setColor(rs.getString("c.color"));
				car.setT_comments(rs.getString("c.t_comments"));
				car.setPrice(rs.getDouble("c.price"));
				car.setRent(rs.getDouble("c.rent"));
				car.setStatu(rs.getInt("c.statu"));
				car.setUseable(rs.getInt("c.useable"));
				Brand brand = new Brand();//品牌
				brand.setId(rs.getInt("b.id"));
				brand.setSname(rs.getString("b.sname"));
				car.setBrand(brand);
				Category cate = new Category();
				cate.setId(rs.getInt("ca.id"));
				cate.setEname(rs.getString("ca.ename"));
				car.setCategory(cate);
				cars.add(car);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			URLPathUtil.close(con, ps, rs);
		}
		for(Car car : cars){
			System.out.println(car);
		}
		return cars;
	}
	/**
	 * 根据价格排序
	 */
	@Override
	public List<Car> findCarByPrice(double rent) {
		List<Car> cars=new ArrayList<Car>();
		Connection con = URLPathUtil.getCon();
		String order="";
		if(rent==2){
			order="asc";
		}else if(rent==1){
			order="desc";
		}
		String sql = "select * from t_car c ,t_brand b ,t_category ca where c.brand_id=b.id and c.category_id=ca.id ORDER BY rent "+order;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()){
				Car car=new Car();
				car.setId(rs.getInt("c.id"));
				car.setModel(rs.getString("c.model"));
				car.setColor(rs.getString("c.color"));
				car.setT_comments(rs.getString("c.t_comments"));
				car.setPrice(rs.getDouble("c.price"));
				car.setRent(rs.getDouble("c.rent"));
				car.setStatu(rs.getInt("c.statu"));
				car.setUseable(rs.getInt("c.useable"));
				Brand brand = new Brand();//品牌
				brand.setId(rs.getInt("b.id"));
				brand.setSname(rs.getString("b.sname"));
				car.setBrand(brand);
				Category cate = new Category();//类别
				cate.setId(rs.getInt("ca.id"));
				cate.setEname(rs.getString("ca.ename"));
				car.setCategory(cate);
			    cars.add(car);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			URLPathUtil.close(con, ps, rs);
		}
		
		return cars;
	}
	/**
	 * 根据类别id查询
	 */
	@Override
	public List<Car> findCarByCategory_id(int category_id) {
		List<Car> cars=new ArrayList<Car>();
		Connection con = URLPathUtil.getCon();
		String sql = "select * from t_car c ,t_brand b ,t_category ca  where c.brand_id=b.id and c.category_id=ca.id and ca.id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, category_id);
			rs = ps.executeQuery();
			while(rs.next()){
				Car car=new Car();
				car.setId(rs.getInt("c.id"));
				car.setCar_number(rs.getString("c.car_number"));
				car.setModel(rs.getString("c.model"));
				car.setColor(rs.getString("c.color"));
				car.setT_comments(rs.getString("c.t_comments"));
				car.setPrice(rs.getDouble("c.price"));
				car.setRent(rs.getDouble("c.rent"));
				car.setStatu(rs.getInt("c.statu"));
				car.setUseable(rs.getInt("c.useable"));
				Brand brand = new Brand();//品牌
				brand.setId(rs.getInt("b.id"));
				brand.setSname(rs.getString("b.sname"));
				car.setBrand(brand);
				Category cate = new Category();//类别
				cate.setId(rs.getInt("ca.id"));
				cate.setEname(rs.getString("ca.ename"));
				car.setCategory(cate);
			cars.add(car);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			URLPathUtil.close(con, ps, rs);
		}
		
		return cars;
	}
	/**
	 * 根据品牌id查询
	 */
	@Override
	public List<Car> findCarByBrand_id(int brand_id) {
		List<Car> cars=new ArrayList<Car>();
		Connection con = URLPathUtil.getCon();
		String sql = "select * from t_car c ,t_brand b ,t_category ca  where  c.brand_id=b.id and c.category_id=ca.id and b.id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, brand_id);
			rs = ps.executeQuery();
			if(rs.next()){
				Car car=new Car();
				car.setId(rs.getInt("c.id"));
				car.setCar_number(rs.getString("c.car_number"));
				car.setModel(rs.getString("c.model"));
				car.setColor(rs.getString("c.color"));
				car.setT_comments(rs.getString("c.t_comments"));
				car.setPrice(rs.getDouble("c.price"));
				car.setRent(rs.getDouble("c.rent"));
				car.setStatu(rs.getInt("c.statu"));
				car.setUseable(rs.getInt("c.useable"));
				Brand brand = new Brand();//品牌
				brand.setId(rs.getInt("b.id"));
				brand.setSname(rs.getString("b.sname"));
				car.setBrand(brand);
				Category cate = new Category();//类别
				cate.setId(rs.getInt("ca.id"));
				cate.setEname(rs.getString("ca.ename"));
				car.setCategory(cate);
			cars.add(car);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			URLPathUtil.close(con, ps, rs);
		}
		
		return cars;
	}
	
	 /**
     * 查询所有品牌
     */
	@Override
	public List<Brand> findBrandAll(){
		List<Brand> cars=new ArrayList<Brand>();
		Connection con = URLPathUtil.getCon();
		String sql = "SELECT * FROM t_brand ";
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				
				Brand brand=new Brand();
				brand.setId(rs.getInt(1));
				brand.setSname(rs.getString(2));
				
				cars.add(brand);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			URLPathUtil.close(con, ps, rs);
		}
		
		return cars;
	}
	/**
	 * 所有类别查询
	 */

	@Override
	public List<Category> findCategoryAll(){
		List<Category> cars=new ArrayList<Category>();
		Connection con = URLPathUtil.getCon();
		String sql = "SELECT * FROM t_category ";
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Category category=new Category();
				category.setId(rs.getInt(1));
				category.setEname(rs.getString(2));
				cars.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			URLPathUtil.close(con, ps, rs);
		}
		return cars;
	}
	/**
	 * 根据汽车ID查询
	 */

	@Override
	public Car findCarByCar_id(int car_id){
		Car car=new Car();
		Connection con = URLPathUtil.getCon();
		String sql = "select * from t_car c ,t_brand b ,t_category ca  where  c.brand_id=b.id and c.category_id=ca.id and  c.id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, car_id);
			rs = ps.executeQuery();
			 while(rs.next()){
				car.setId(rs.getInt("c.id"));
				car.setCar_number(rs.getString("c.car_number"));
				car.setModel(rs.getString("c.model"));
				car.setColor(rs.getString("c.color"));
				car.setT_comments(rs.getString("c.t_comments"));
				car.setPrice(rs.getDouble("c.price"));
				car.setRent(rs.getDouble("c.rent"));
				car.setStatu(rs.getInt("c.statu"));
				car.setUseable(rs.getInt("c.useable"));
				Brand brand = new Brand();//品牌
				brand.setId(rs.getInt("b.id"));
				brand.setSname(rs.getString("b.sname"));
				car.setBrand(brand);
				Category cate = new Category();//类别
				cate.setId(rs.getInt("ca.id"));
				cate.setEname(rs.getString("ca.ename"));
				car.setCategory(cate);		
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			URLPathUtil.close(con, ps, rs);
		}
		return car;

	}
	/**
	 * 修改汽车状态
	 */

	@Override
	public int updateCar1(int useable, int id){
		int row=0;
		Connection con = URLPathUtil.getCon();
		String sql="UPDATE t_car SET statu=? WHERE id=?";
		PreparedStatement ps=null;
		try {
			ps=con.prepareStatement(sql);
			
			ps.setInt(1,useable);
			ps.setInt(2, id);
			 row = ps.executeUpdate();
			System.out.println(row+"记录修改已成功");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			URLPathUtil.close(con, ps, null);
		}
		return row;
		
	}
}
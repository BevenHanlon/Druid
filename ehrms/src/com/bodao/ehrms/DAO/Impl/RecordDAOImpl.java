package com.bodao.ehrms.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bodao.ehrms.DAO.RecordDAO;
import com.bodao.ehrms.entity.Brand;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.entity.Category;
import com.bodao.ehrms.entity.Record;
import com.bodao.ehrms.entity.User;
import com.bodao.ehrms.util.URLPathUtil;

public class RecordDAOImpl implements RecordDAO {
	
	/**
	 *查询全部租车信息
	 */
		@Override
		public List<Record> findAll() {
			List<Record> oo=new ArrayList<Record>();
			List<Record> Reorde = new ArrayList<Record>();
			Connection con = URLPathUtil.getCon();
			String sql = "SELECT t.id,t.payment,t.return_date,t.start_date,t.user_id ,u.username,c.id,c.t_comments,c.rent,ca.ename,b.sname FROM t_record t ,t_user u,t_car c,t_brand b,t_category ca \r\n" + 
					"WHERE t.car_id=c.id AND t.user_id=u.id AND c.category_id=ca.id AND c.brand_id=b.id;";
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					Record record = new Record();
					record.setId(rs.getInt(1));
					record.setPayment(rs.getDouble(2));
					record.setReturn_date(rs.getDate(3));
					record.setStart_date(rs.getDate(4));
					User user=new User();
					user.setId(rs.getInt(5));
					user.setName(rs.getString(6));
					record.setUser(user);
					Car car=new Car();
					car.setId(rs.getInt(7));
					car.setT_comments(rs.getString(8));
					car.setRent(rs.getDouble(9));
					Category category=new Category();
					category.setEname(rs.getString(10));
					car.setCategory(category);
					Brand brand=new Brand();
					brand.setSname(rs.getString(11));
					car.setBrand(brand);
					record.setCar(car);
						oo.add(record);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				URLPathUtil.close(con, ps, rs);
			}
			for (Record record : Reorde) {
				System.out.println(record);
			}
			return oo;
		}
		/**
		 * 查询指定用户租车记录
		 */
		@Override
		public List<Record> findRecordByRecordUser_id(int user_id) {
			List<Record> oo=new ArrayList<Record>();
			Connection con = URLPathUtil.getCon();
			String sql = "SELECT r.id,r.car_id,c.model,c.t_comments,cate.ename,b.sname ,r.start_date,r.return_date,r.payment  FROM t_Record r,t_car c,t_brand b,t_category cate" + 
					" WHERE r.car_id=c.id AND c.brand_id=b.id AND c.category_id=cate.id AND r.user_id=?";
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, user_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					Record record = new Record();
					record.setId(rs.getInt(1));
					Car car=new Car();
					car.setId(rs.getInt(2));
					car.setModel(rs.getString(3));
					car.setT_comments(rs.getString(4));
					Category category=new Category();
					category .setEname(rs.getString(5));
					car.setCategory(category);
					Brand brand=new Brand();
					brand.setSname(rs.getString(6));
					car.setBrand(brand);
					record.setCar(car);
					record.setStart_date(rs.getDate(7));
					record.setReturn_date(rs.getDate(8));
					record.setPayment(rs.getDouble(9));
					oo.add(record);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				URLPathUtil.close(con, ps, rs);
			}
			return oo;
		}
		/**
		 * 租车
		 */
		@Override
		public int addrecord(int user_id, int car_id) {
			int key=-1;
			Connection con = URLPathUtil.getCon();
			PreparedStatement ps= null;
			ResultSet rs=null;
			String sql = "insert into t_record(user_id,car_id,start_date) values(?,?,?)";
			try {
				ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setInt(1,user_id);
				ps.setInt(2,car_id);
				ps.setString(3,new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date()));
				ps.executeUpdate();
				rs=ps.getGeneratedKeys();
				if(rs.next()) {
					key = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				URLPathUtil.close(con, ps, null);
			}
			
			return key;
		}
		/**
		 * 还车
		 */
		@SuppressWarnings("resource")
		@Override
		public int updaterecord(double rent, int user_id, int car_id) {
			int key=-1;
			Connection con = URLPathUtil.getCon();
			PreparedStatement ps = null;
			String sql ="update t_record set return_date=? where user_id=? and car_id=?";
			String sql1="update t_record set payment=	if(datediff(return_date,start_date)=0,1,datediff(return_date,start_date))*"+rent+"where user_id=? and car_id=?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1,new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date()));
				ps.setInt(2,user_id);
				ps.setInt(3,car_id);
				ps.executeUpdate();
				ps=con.prepareStatement(sql1);
				ps.setInt(1, user_id);
				ps.setInt(2, car_id);
				key = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				URLPathUtil.close(con, ps, null);
			}
			return key;
		}
		/**
		 * 查询汽车信息
		 */
		@Override
		public Record select(int id){	
			Record record = new Record();	
			Connection con = URLPathUtil.getCon();
		    String sql = "SELECT r.id,r.car_id,c.model,c.t_comments,ca.ename,b.sname ,r.start_date,r.payment,c.rent,r.return_date FROM t_Record r,t_car c,t_brand b,t_category ca\r\n" + 
				" WHERE r.car_id=c.id AND c.brand_id=b.id AND c.category_id=ca.id and r.id=?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				record.setId(rs.getInt(1));
				Car car=new Car();
				car.setId(rs.getInt(2));
				car.setModel(rs.getString(3));
				car.setT_comments(rs.getString(4));
				Category category=new Category();
				category .setEname(rs.getString(5));
				car.setCategory(category);
				Brand brand=new Brand();
				brand.setSname(rs.getString(6));
				car.setBrand(brand);
				record.setStart_date(rs.getDate(7));
				record.setPayment(rs.getDouble(8));
				car.setRent(rs.getDouble(9));
				record.setReturn_date(rs.getDate(10));
				record.setCar(car);				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return record;
	}
		/**
		 * 根据用户id和汽车id查询租赁
		 */
		@SuppressWarnings("finally")
		@Override
		public Record findRecord(int user_id, int car_id){
			Record record=new Record();
			Connection con = URLPathUtil.getCon();
			String sql = "SELECT r.id,r.car_id,c.model,c.rent,c.t_comments,ca.ename,b.sname ,r.start_date,r.payment,r.return_date FROM  t_Record r,t_car c,t_brand b,t_category ca WHERE r.car_id=c.id AND c.brand_id=b.id AND c.category_id=ca.id and r.id=(SELECT MAX(id)FROM t_record)";			 
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {				
					record.setId(rs.getInt(1));
					Car car=new Car();
					car.setId(rs.getInt(2));
					car.setModel(rs.getString(3));
					car.setRent(rs.getDouble(4));
					car.setT_comments(rs.getString(5));
					Category category=new Category();
					category .setEname(rs.getString(6));
					car.setCategory(category);
					Brand brand=new Brand();
					brand.setSname(rs.getString(7));
					car.setBrand(brand);
					record.setCar(car);
					record.setStart_date(rs.getDate(8));
					record.setPayment(rs.getDouble(9));
					record.setReturn_date(rs.getDate(10));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				URLPathUtil.close(con, ps, rs);
			return record;
		}
	}	

}

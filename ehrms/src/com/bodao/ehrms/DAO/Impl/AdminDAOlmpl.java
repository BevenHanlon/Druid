package com.bodao.ehrms.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bodao.ehrms.DAO.AdminDAO;
import com.bodao.ehrms.entity.User;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * 管理员impl
 * @author 59112
 *
 */
public class AdminDAOlmpl implements AdminDAO{
	
	public User findUserByNameAndPwd(String name, String pwd,int ytpe){
		User admin1=null;	
		Connection con=URLPathUtil.getCon();
		String sql ="select * from t_user where username=?  and pwd=?and ytpe=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pwd);
			ps.setInt(3, ytpe);
			rs=ps.executeQuery();
			if(rs.next()) {
				admin1 =new User();	
				admin1.setId(rs.getInt(1));
				admin1.setName(rs.getString(2));
				admin1.setPwd(rs.getString(3));
				admin1.setYtpe(rs.getInt(8));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}finally {
			URLPathUtil.close(con, ps, rs);
		}
		return admin1;
		
		
	}
     //用户注册
	public int add(User user){
		int row=0;
		Connection on= URLPathUtil.getCon();
		String sql="INSERT INTO t_user"+"(username,pwd,sex,id_card,tel,addr)"+"VALUES(?,?,?,?,?,?)";
		PreparedStatement ps=null;
		 try{
			ps=on.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPwd());
			ps.setInt(3, user.getSex());
			ps.setString(4,user.getIdcard());
			ps.setString(5, user.getTel());
			ps.setString(6, user.getAddress());
			row=ps.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}finally {
			URLPathUtil.close(on, ps, null);
		}
	
		return row;
		}
	}



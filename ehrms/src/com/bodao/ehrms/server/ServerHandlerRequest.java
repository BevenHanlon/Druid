package com.bodao.ehrms.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.bodao.ehrms.entity.Brand;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.entity.Category;
import com.bodao.ehrms.entity.Record;
import com.bodao.ehrms.entity.User;
import com.bodao.ehrms.service.AdminService;
import com.bodao.ehrms.service.UserService;
import com.bodao.ehrms.util.URLPathUtil;

public class ServerHandlerRequest implements Runnable {
	Socket s = null;
	BufferedReader br  = null;
	PrintWriter pw = null;
	UserService us = new UserService();
	AdminService as = new AdminService();
	public ServerHandlerRequest(Socket s) {
		this.s = s;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		System.out.println("server处理请求");
		//接收发过来的数据 login?username=ddd&pwd=1234&type=1
		try {//findAllCar
			String url = br.readLine();
			String response = "";
			//处理路径
			String path = URLPathUtil.getPath(url);
			//获取参数
			Map<String,String> map = URLPathUtil.getParams(url);
			switch (path) {
			case URLPathUtil.FIND_ALL_CAR://查询所有汽车
				response = handlerFindAllCar();
				break;
			case URLPathUtil.LOGIN://登录
				response = handlerLogin(map);
				break;
			case URLPathUtil.REGISTER://注册	
				response = handlerRegister(map);
				break;
			case URLPathUtil.SORT://排序
				response = handlerSort(map);
				break;
			case URLPathUtil.FIND_ALL_CATEGORY://查询类型
				response = handlerFindAllCategory(map);
				break;
			case URLPathUtil.FIND_ONE_CATEGORY://根据类型编号查询路径
				response = handlerFindOneCategory(map);
				break;
			case URLPathUtil.FIND_ALL_BRAND://查询品牌
				response = handlerFindAllBrand(map);
				break;
			case URLPathUtil.FIND_ONE_BRAND://根据品牌编号查询路径
				response = handlerFindOneBrand(map);
				break;
			case URLPathUtil.FIND_ALL_RECORD://查询租赁记录
				response = handlerFindAllRecord(map);
				break;
			case URLPathUtil.ADD_CAR://添加汽车
				response = handlerAddCar(map);
				break;
			case URLPathUtil.UPDATE_CAR://修改汽车
				response = handlerUpdateCar(map);
				break;
			case URLPathUtil.USER_LEASE_CAR://租车
				response = handlerUserLeaseCar(map);
				break;
			case URLPathUtil.USER_RETURN_CAR://还车
				response = handlerUserReturnCar(map);
				break;
			case URLPathUtil.FIND_ONE_CAR://查询指定汽车
				response = handlerFindOneCar(map);
				break;
			case URLPathUtil.FIND_ONE_USER://查询指定用户租赁记录
				response = handlerFindOneUser(map);
				break;
			}
			pw.println(response);
			pw.flush(); 
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				s.close();
				br.close();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		

	}

	
	//根据品牌编号查询路径
	private String handlerFindOneBrand(Map<String, String> map) {
		int brand =Integer.parseInt(map.get("bid"));
		List<Car> bid =us.selectbrandid(brand);
		return JSON.toJSONString(bid);
	}
	//根据类型编号查询路径
	private String handlerFindOneCategory(Map<String, String> map) {
		int cid = Integer.parseInt(map.get("cid"));
		List<Car> cate=us.selectcategoryid(cid);
		return JSON.toJSONString(cate);
	}
	//排序
	private String handlerSort(Map<String, String> map) {
		int choose=Integer.parseInt(map.get("choose"));
		List<Car>cars =us.selsetprice(choose);	
		return JSON.toJSONString(cars);
	}
	//查询指定用户租赁记录
	private String handlerFindOneUser(Map<String, String> map) {
		int record =Integer.parseInt(map.get("user_id"));
		List<Record> recorde=us.selescrecorduserid(record);
		return JSON.toJSONString(recorde);
	}
	//查询指定汽车
	private String handlerFindOneCar(Map<String, String> map) {
		int cid = Integer.parseInt(map.get("id"));
		Car car=as.selectCarId(cid);
		return JSON.toJSONString(car);
	}
	//还车
		private String handlerUserReturnCar(Map<String, String> map) {
				int user_id =Integer.parseInt(map.get("user_id"));
				int car_id =Integer.parseInt(map.get("car_id"));
				Record rid =us.returncar(user_id, car_id);
				return JSON.toJSONString(rid);
	}
	//租车
	private String handlerUserLeaseCar(Map<String, String> map) {
		int user_id =Integer.parseInt(map.get("user_id"));
		int car_id =Integer.parseInt(map.get("car_id"));
		Record rid =us.rentcar(user_id, car_id);
		return JSON.toJSONString(rid);

	}
	//修改汽车信息
	private String handlerUpdateCar(Map<String, String> map) {
		String response=null;
		int id=Integer.parseInt(map.get("id"));
		int cho =Integer.parseInt(map.get("cho"));
		int cho1 =Integer.parseInt(map.get("cho1"));
		int update =as.updata(cho, cho1, id);
		if (update==1) {
			response= "修改成功";
		}else{
			response="修改失败";
		}
		return response;
	}
	//添加汽车
	private String handlerAddCar(Map<String, String> map) {
		String response = null;
		int brandid =Integer.parseInt(map.get("brand")) ;
		int categoryid =Integer.parseInt(map.get("cate"));
		String model =map.get("model");
		String car_num = map.get("car_num");
		String comment = map.get("comment");
		String color =map.get("color");
		double price =Double.parseDouble(map.get("price"));
		double rent =Double.parseDouble(map.get("rent"));
		int statu = Integer.parseInt(map.get("statu"));
		int useable =Integer.parseInt(map.get("useable"));
		Brand brand = new Brand(brandid,null);
		Category category = new Category(categoryid,null);
		Car cars = new Car(brand, category, car_num, model, color, comment, price, rent, statu, useable);
	    boolean row=as.addCar(cars);
		if (row) {
			response= "添加成功";
		}else{
			response="添加失败";
		}
		return response;
	}
	//查询所有租赁记录
	private String handlerFindAllRecord(Map<String, String> map) {
		List<Record> record = as.selectrecord();
		return JSON.toJSONString(record);
	}
	//查询所有品牌
	private String handlerFindAllBrand(Map<String, String> map) {
		List<Brand> brands = as.selescBrandAll();
		return JSON.toJSONString(brands);
	}
	//查询所有类型
	private String handlerFindAllCategory(Map<String, String> map) {
		List<Category> category = as.selectCategoryAll();
		return JSON.toJSONString(category);
	}
	//注册
	private String handlerRegister(Map<String, String> map) {
		String response = null;
		String username = map.get("username");
		String pwd = map.get("pwd");
		int sex =Integer.parseInt(map.get("sex"));
		String id_card =map.get("id_card");
		String tel =map.get("tel");
		String addr = map.get("addr");
		User user1 =new User(username, pwd, sex, id_card, tel, addr, 0);
		boolean f = us.register(user1);
		if (f) {
			response="1";
		}else{
			response="2";
		}
		return response;
	}
	//登录
	private String handlerLogin(Map<String, String> map) {
		String username =map.get("username");
		String pwd = map.get("pwd");
		int type = Integer.parseInt(map.get("type"));	
		User user = as.login(username, pwd,type);
		String response=JSON.toJSONString(user);
		return response;
	}
	//查询全部汽车
	private String  handlerFindAllCar() {
		List<Car> cars = us.select();
		//将集合转成json字符串发送给客户端
		return JSON.toJSONString(cars);
	}
	
}

package com.bodao.ehrms.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Brand;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.entity.Category;
import com.bodao.ehrms.entity.User;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * 添加汽车
 * @author 59112
 *
 */
public class AdminAddCar extends Client{
	public void AddCar(){
		Car cars = new Car();
		Scanner sc = new Scanner(System.in);
		System.out.println("=======================");
		System.out.println("请分别输入以下信息：");
		System.out.println("-----------------------------------------");
		System.out.println("品牌如下:");
		System.out.println("品牌编号"+"\t品牌名");
		String url =URLPathUtil.FIND_ALL_BRAND;
		String req =request(url);
		List<Brand> list= JSON.parseObject(req, new TypeReference<List<Brand>>(){}.getType());
		for (Brand b : list) {
			System.out.println(" "+b.getId()+"\t\t"+b.getSname());
		}
		System.out.print("请选择品牌编号：");
		int brand = sc.nextInt();
		System.out.println("----------------------------------------");
		System.out.println("类型如下：");
		System.out.println("类型编号"+"\t类型名");
		String url1 =URLPathUtil.FIND_ALL_CATEGORY;
		String req1 =request(url1);
		List<Category> list1= JSON.parseObject(req1, new TypeReference<List<Category>>(){}.getType());
		for (Category c : list1) {
			System.out.println(c.getId()+"\t"+c.getEname());
		}
		System.out.print("请选择类型编号：");
		int cate =sc.nextInt();
		System.out.println("---------------------------------");
		System.out.print("型号：");
		String model = sc.next();
		System.out.println("---------------------------------");
		System.out.print("车牌号：");
		String car_num = sc.next();
		System.out.println("---------------------------------");
		System.out.print("概要：");
		String comment = sc.next();
		System.out.println("---------------------------------");
		System.out.print("颜色：");
		String color = sc.next();
		System.out.println("---------------------------------");
		System.out.print("汽车价格：");
		String price = sc.next();
		System.out.println("---------------------------------");
		System.out.print("每日租金：");
		String rent = sc.next();
		System.out.println("---------------------------------");
		System.out.print("是否可借(0:可借1:不可借)：");
		String statu = sc.next();
		System.out.println("---------------------------------");
		System.out.print("是否上架(0:上架1:下架):");
		String useable = sc.next();
		String url2 =URLPathUtil.ADD_CAR+"?brand="+brand+"&cate="+cate+"&model="+model+"&car_num="+car_num+"&comment="+comment
				+"&color="+color+"&price="+price+"&rent="+rent+"&statu="+statu+"&useable="+useable; 
		String addCarResponse = request(url2);
		System.out.println(addCarResponse);
	} 
	
}

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
 * �������
 * @author 59112
 *
 */
public class AdminAddCar extends Client{
	public void AddCar(){
		Car cars = new Car();
		Scanner sc = new Scanner(System.in);
		System.out.println("=======================");
		System.out.println("��ֱ�����������Ϣ��");
		System.out.println("-----------------------------------------");
		System.out.println("Ʒ������:");
		System.out.println("Ʒ�Ʊ��"+"\tƷ����");
		String url =URLPathUtil.FIND_ALL_BRAND;
		String req =request(url);
		List<Brand> list= JSON.parseObject(req, new TypeReference<List<Brand>>(){}.getType());
		for (Brand b : list) {
			System.out.println(" "+b.getId()+"\t\t"+b.getSname());
		}
		System.out.print("��ѡ��Ʒ�Ʊ�ţ�");
		int brand = sc.nextInt();
		System.out.println("----------------------------------------");
		System.out.println("�������£�");
		System.out.println("���ͱ��"+"\t������");
		String url1 =URLPathUtil.FIND_ALL_CATEGORY;
		String req1 =request(url1);
		List<Category> list1= JSON.parseObject(req1, new TypeReference<List<Category>>(){}.getType());
		for (Category c : list1) {
			System.out.println(c.getId()+"\t"+c.getEname());
		}
		System.out.print("��ѡ�����ͱ�ţ�");
		int cate =sc.nextInt();
		System.out.println("---------------------------------");
		System.out.print("�ͺţ�");
		String model = sc.next();
		System.out.println("---------------------------------");
		System.out.print("���ƺţ�");
		String car_num = sc.next();
		System.out.println("---------------------------------");
		System.out.print("��Ҫ��");
		String comment = sc.next();
		System.out.println("---------------------------------");
		System.out.print("��ɫ��");
		String color = sc.next();
		System.out.println("---------------------------------");
		System.out.print("�����۸�");
		String price = sc.next();
		System.out.println("---------------------------------");
		System.out.print("ÿ�����");
		String rent = sc.next();
		System.out.println("---------------------------------");
		System.out.print("�Ƿ�ɽ�(0:�ɽ�1:���ɽ�)��");
		String statu = sc.next();
		System.out.println("---------------------------------");
		System.out.print("�Ƿ��ϼ�(0:�ϼ�1:�¼�):");
		String useable = sc.next();
		String url2 =URLPathUtil.ADD_CAR+"?brand="+brand+"&cate="+cate+"&model="+model+"&car_num="+car_num+"&comment="+comment
				+"&color="+color+"&price="+price+"&rent="+rent+"&statu="+statu+"&useable="+useable; 
		String addCarResponse = request(url2);
		System.out.println(addCarResponse);
	} 
	
}

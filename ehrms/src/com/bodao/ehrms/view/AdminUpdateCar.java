package com.bodao.ehrms.view;

import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.entity.Category;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * �޸�������Ϣ
 * @author 59112
 *
 */
public class AdminUpdateCar extends Client{
	public void UpdateCar(){
		Scanner sc = new Scanner(System.in);
		System.out.println("==================");
		System.out.println("��������Ҫ�޸ĵ�������ţ�");
		int id =sc.nextInt();
		String url = URLPathUtil.FIND_ONE_CAR+"?id="+id;
		String req =request(url);
		Car car =JSON.parseObject(req, new TypeReference<Car>(){}.getType());
		if (car.getStatu()==0) {
			System.out.println("=========================================");
			System.out.println("���"+"\t��������"+"\t��ע"+"\t\tƷ��"+"\t\t����"+"\t\t�����ܼ۸�"+"\t\t���޼۸�"+"\t�Ƿ����"+"\t�Ƿ��ϼ�");
			System.out.println(car.getId()+"\t"+car.getBrand().getSname()+"\t\t"+car.getT_comments()+"\t"+car.getModel()+"\t\t"+car.getCategory().getEname()
					+"\t"+car.getPrice()+"/Ԫ"+"\t"+car.getRent()+"/��"+"\t\t"+car.getStatu()+"\t\t"+car.getUseable());
			System.out.println("������Ҫ�޸ĵ����ݵı�ţ�");
			System.out.println("1.���޼۸�"+"2.�ϼ��¼�");	
			System.out.println("�������µ����޼۸�");
			int cho = sc.nextInt();
			System.out.println("��ѡ���Ƿ��ϼܣ�");
			int cho1 = sc.nextInt();
			String url1 = URLPathUtil.UPDATE_CAR+"?id="+id+"&cho="+cho+"&cho1="+cho1;
			String update =request(url1);	
			System.out.println(update);
			String url2 =URLPathUtil.FIND_ONE_CAR+"?id="+id; 
			String find =request(url2);
			Car car1 = JSON.parseObject(find, new TypeReference<Car>(){}.getType());
			System.out.println("���"+"\t��������"+"\t��ע"+"\t\tƷ��"+"\t\t����"+"\t\t�����ܼ۸�"+"\t\t���޼۸�"+"\t�Ƿ����"+"\t�Ƿ��ϼ�");
			System.out.println(car1.getId()+"\t"+car1.getBrand().getSname()+"\t\t"+car1.getT_comments()+"\t"+car1.getModel()+"\t\t"+car1.getCategory().getEname()
					+"\t"+car1.getPrice()+"/Ԫ"+"\t"+car1.getRent()+"/��"+"\t\t"+car1.getStatu()+"\t\t"+car1.getUseable());
		}else{
			System.out.println("�����Ѿ����⣬���ܽ����޸Ĳ�����");
		}
	}

}

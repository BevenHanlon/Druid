package com.bodao.ehrms.view;

import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.entity.User;
import com.bodao.ehrms.util.URLPathUtil;
/**
 * ����
 * @author 59112
 *
 */
public class SortCar extends Client{
	public List<Car> sort(){
		Scanner sc = new Scanner(System.in);
		System.out.println("====================");
		System.out.println("����1 ���۸������� "+"2 ���۸���������");
		int choose = sc.nextInt();
		String url =URLPathUtil.SORT+"?choose="+choose;
		String req =request(url);
		List<Car> list = JSON.parseObject(req, new TypeReference<List<Car>>(){}.getType());
		System.out.println("���"+"\t��������"+"\t  ��ע"+"\t\tƷ��"+"\t\t  ����"+"\t\t  �۸�"+"\t\t�Ƿ����");
		for (Car car : list) {
			System.out.println(car.getId()+"\t  "+car.getModel()+"\t\t"+car.getT_comments()+"\t"+car.getBrand().getSname()+"\t\t"+car.getCategory().getEname()+"\t"+car.getRent()+"/��"+"\t  "+(car.getStatu()==0?"����":"������"));
		}
		return list;
	}
}

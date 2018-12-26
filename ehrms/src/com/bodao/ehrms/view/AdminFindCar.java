package com.bodao.ehrms.view;

import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Car;
/**
 * 管理员查询界面
 * @author 59112
 *
 */
public class AdminFindCar extends Client {
	public List<Car> AdminFindCar(){
		Scanner sc = new Scanner(System.in);
		System.out.println("======================================================");
		System.out.println("编号"+"\t汽车名称"+"\t\t备注"+"\t\t品牌"+"\t\t类型"+"\t\t价格"+"\t\t是否可租"+"\t是否上架");
		String url="findallcar";
		String response = request(url);//集合编程json字符串
		List<Car> list = JSON.parseObject(response,new TypeReference<List<Car>>(){}.getType());
		for (Car car : list) {
			System.out.println(car.getId()+"\t"+car.getModel()+"\t\t    "+car.getT_comments()+"\t\t"+car.getBrand().getSname()+"\t\t"+car.getCategory().getEname()+"\t"+car.getRent()+"/天"+"\t  "+(car.getStatu()==0?"可租":"不可租")+"\t\t"+(car.getUseable()==0?"上架":"下架"));
		}
		return list;

	}

}

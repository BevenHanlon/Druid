package com.bodao.ehrms.view;

import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * 用户查询界面
 * @author 59112
 *
 */
public class UserFindCar extends Client{
	public List<Car> UserFindCar(){
		String url=URLPathUtil.FIND_ALL_CAR;
		String req= request(url);//集合编程json字符串
		System.out.println("================================================");
		System.out.println("编号"+"\t汽车名称"+"\t  备注"+"\t\t\t品牌"+"\t\t  类型"+"\t\t  价格"+"\t\t是否可租");
		List<Car> list = JSON.parseObject(req,new TypeReference<List<Car>>(){}.getType());
		for (Car car : list) {
			System.out.println(car.getId()+"\t\t  "+car.getModel()+"\t\t"+car.getT_comments()+"\t\t\t"+car.getBrand().getSname()+"\t\t"+car.getCategory().getEname()+"\t\t"+car.getRent()+"/天"+"\t\t"+(car.getStatu()==0?"可租":"不可租"));
		}
		return list;
	}
	

}

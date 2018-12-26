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
 * 排序
 * @author 59112
 *
 */
public class SortCar extends Client{
	public List<Car> sort(){
		Scanner sc = new Scanner(System.in);
		System.out.println("====================");
		System.out.println("输入1 按价格降序排序 "+"2 按价格升序排序");
		int choose = sc.nextInt();
		String url =URLPathUtil.SORT+"?choose="+choose;
		String req =request(url);
		List<Car> list = JSON.parseObject(req, new TypeReference<List<Car>>(){}.getType());
		System.out.println("编号"+"\t汽车名称"+"\t  备注"+"\t\t品牌"+"\t\t  类型"+"\t\t  价格"+"\t\t是否可租");
		for (Car car : list) {
			System.out.println(car.getId()+"\t  "+car.getModel()+"\t\t"+car.getT_comments()+"\t"+car.getBrand().getSname()+"\t\t"+car.getCategory().getEname()+"\t"+car.getRent()+"/天"+"\t  "+(car.getStatu()==0?"可租":"不可租"));
		}
		return list;
	}
}

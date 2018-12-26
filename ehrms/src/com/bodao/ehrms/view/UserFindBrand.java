package com.bodao.ehrms.view;

import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.util.URLPathUtil;
/**
 * 品牌搜索
 * @author 59112
 *
 */
public class UserFindBrand extends Client{
	public List<Car> FindBrand() {
		Scanner sc= new Scanner(System.in);
		System.out.println("请输入品牌编号:1.大众2.三菱3.起亚4.奔驰5.丰田6.奥迪7.福特");
		int bid = sc.nextInt();
		String url =URLPathUtil.FIND_ONE_BRAND+"?bid="+bid;
		String req =request(url);
		System.out.println("===================================================");
		System.out.println("编号"+"\t  汽车名称"+"\t\t  备注"+"\t\t\t品牌"+"\t\t  类型"+"\t\t  价格"+"\t\t是否可租");
		List<Car> list = JSON.parseObject(req,new TypeReference<List<Car>>(){}.getType());
		for (Car car : list) {
			System.out.println(car.getId()+"\t\t"+car.getModel()+"\t\t\t"+car.getT_comments()+"\t\t\t"+car.getBrand().getSname()+"\t\t"+car.getCategory().getEname()+"\t\t"+car.getRent()+"/天"+"\t\t"+(car.getStatu()==0?"可租":"不可租"));
		}
		return list;
	}
	
}

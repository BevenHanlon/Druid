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
 * 修改汽车信息
 * @author 59112
 *
 */
public class AdminUpdateCar extends Client{
	public void UpdateCar(){
		Scanner sc = new Scanner(System.in);
		System.out.println("==================");
		System.out.println("请输入你要修改的汽车编号：");
		int id =sc.nextInt();
		String url = URLPathUtil.FIND_ONE_CAR+"?id="+id;
		String req =request(url);
		Car car =JSON.parseObject(req, new TypeReference<Car>(){}.getType());
		if (car.getStatu()==0) {
			System.out.println("=========================================");
			System.out.println("编号"+"\t汽车名称"+"\t备注"+"\t\t品牌"+"\t\t类型"+"\t\t汽车总价格"+"\t\t租赁价格"+"\t是否可租"+"\t是否上架");
			System.out.println(car.getId()+"\t"+car.getBrand().getSname()+"\t\t"+car.getT_comments()+"\t"+car.getModel()+"\t\t"+car.getCategory().getEname()
					+"\t"+car.getPrice()+"/元"+"\t"+car.getRent()+"/天"+"\t\t"+car.getStatu()+"\t\t"+car.getUseable());
			System.out.println("请输入要修改的内容的编号：");
			System.out.println("1.租赁价格"+"2.上架下架");	
			System.out.println("请输入新的租赁价格：");
			int cho = sc.nextInt();
			System.out.println("请选择是否上架：");
			int cho1 = sc.nextInt();
			String url1 = URLPathUtil.UPDATE_CAR+"?id="+id+"&cho="+cho+"&cho1="+cho1;
			String update =request(url1);	
			System.out.println(update);
			String url2 =URLPathUtil.FIND_ONE_CAR+"?id="+id; 
			String find =request(url2);
			Car car1 = JSON.parseObject(find, new TypeReference<Car>(){}.getType());
			System.out.println("编号"+"\t汽车名称"+"\t备注"+"\t\t品牌"+"\t\t类型"+"\t\t汽车总价格"+"\t\t租赁价格"+"\t是否可租"+"\t是否上架");
			System.out.println(car1.getId()+"\t"+car1.getBrand().getSname()+"\t\t"+car1.getT_comments()+"\t"+car1.getModel()+"\t\t"+car1.getCategory().getEname()
					+"\t"+car1.getPrice()+"/元"+"\t"+car1.getRent()+"/天"+"\t\t"+car1.getStatu()+"\t\t"+car1.getUseable());
		}else{
			System.out.println("汽车已经出租，不能进行修改操作！");
		}
	}

}

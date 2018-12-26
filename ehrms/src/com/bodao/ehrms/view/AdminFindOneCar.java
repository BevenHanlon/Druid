package com.bodao.ehrms.view;

import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * 查询指定汽车信息
 * @author 59112
 *
 */
public class AdminFindOneCar extends Client {
	public void findone(){
		Scanner sc =new Scanner(System.in);
		System.out.println("===========================");
		System.out.println("请输入指定汽车编号：");
		int id = sc.nextInt();
		String url=URLPathUtil.FIND_ONE_CAR+"?id="+id;
		String response = request(url);//集合编程json字符串
		Car car = JSON.parseObject(response,new TypeReference<Car>(){}.getType());
		System.out.println("编号"+"\t汽车名称"+"\t\t备注"+"\t\t品牌"+"\t\t类型"+"\t\t价格"+"\t\t是否可租"+"\t是否上架");
		System.out.println(car.getId()+"\t"+car.getModel()+"\t\t    "+car.getT_comments()+"\t\t"+car.getBrand().getSname()+"\t\t"+car.getCategory().getEname()+"\t"+car.getRent()+"/天"+"\t  "+(car.getStatu()==0?"可租":"不可租")+"\t\t"+(car.getUseable()==0?"上架":"下架"));
	}
	
}

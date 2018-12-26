package com.bodao.ehrms.view;

import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * 类型搜索
 * @author 59112
 *
 */
public class UserFindCategory extends Client{
		public List<Car> findcategory(){
			Scanner sc = new Scanner(System.in);
			System.out.println("请输入类型编号：1.舒适性2.精英型3.紧凑型4.豪华型");
			int cid = sc.nextInt();
			String url =URLPathUtil.FIND_ONE_CATEGORY+"?cid="+cid;
			String req =request(url);
			System.out.println("=========================================");
			System.out.println("编号"+"\t汽车名称"+"\t\t备注"+"\t\t品牌"+"\t\t  类型"+"\t\t  价格"+"\t\t是否可租");
			List<Car> list = JSON.parseObject(req,new TypeReference<List<Car>>(){}.getType());
			for (Car car : list) {
				System.out.println(car.getId()+"\t\t"+car.getModel()+"\t\t\t"+car.getT_comments()+"\t\t\t"+car.getBrand().getSname()+"\t\t\t"+car.getCategory().getEname()+"\t\t"+car.getRent()+"/天"+"\t\t"+(car.getStatu()==0?"可租":"不可租"));
			}
			return list;
		}

	
}

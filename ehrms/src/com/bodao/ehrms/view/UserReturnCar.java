package com.bodao.ehrms.view;

import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Record;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * 还车界面
 * @author 59112
 *
 */
public class UserReturnCar extends Client{
	public void ReturnCar(int id){
		Scanner sc = new Scanner(System.in);
		System.out.println("==================");
		System.out.println("输入要还的汽车编号");
		int car_id=sc.nextInt();
		String url=URLPathUtil.USER_RETURN_CAR+"?car_id="+car_id+"&user_id="+id;
		String req =request(url);
		Record record = JSON.parseObject(req, new TypeReference<Record>(){}.getType());
		System.out.println("还车成功！"+"还车信息如下：");
		System.out.println("编号"+"\t汽车名称"+"\t每日租金"+"\t总租金"+"\t\t  备注"+"\t\t品牌"+"\t\t类型"+"\t\t  借车时间"+"\t\t  还车时间");
		System.out.println(record.getId()+"\t\t"+record.getCar().getModel()+"\t\t "+record.getCar().getRent()+"/天"+"\t"+record.getPayment()+"/元"+"\t\t"+record.getCar().getT_comments()+"\t"+record.getCar().getBrand().getSname()+"\t\t"+record.getCar().getCategory().getEname()+"\t"+record.getStart_date()+"\t"+record.getReturn_date());
			
		}

}

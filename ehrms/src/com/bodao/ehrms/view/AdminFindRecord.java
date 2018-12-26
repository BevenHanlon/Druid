package com.bodao.ehrms.view;

import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.entity.Record;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * 管理员查看汽车记录界面，默认查看全部租赁记录
 * @author 59112
 *
 */
public class AdminFindRecord extends Client{
	public List<Record> AdminFindRecord(){
		Scanner sc = new Scanner(System.in);
		String url =URLPathUtil.FIND_ALL_RECORD;
		String req =request(url);
		System.out.println("===========================================================================");
		System.out.println("编号"+"\t汽车编号"+"\t汽车名称"+"\t用户编号"+"\t用户名"+"\t每日租金"+"\t租金总额"+"\t  备注"+"\t\t品牌"+"\t\t类型"+"\t\t  借车时间"+"\t\t 还车时间");
		List<Record> list =JSON.parseObject(req,new TypeReference<List<Record>>(){}.getType());
		for (Record record : list) {
			System.out.println(record.getId()+"\t  "+record.getCar().getId()+"\t\t  "+record.getCar().getBrand().getSname()+"\t\t  "+record.getUser().getId()
					+"\t\t"+record.getUser().getName()+"\t\t"+record.getCar().getRent()+"/天"+"\t"+record.getPayment()+"/元"+"\t"+record.getCar().getT_comments()
					+"\t"+record.getCar().getBrand().getSname()+"\t\t"+record.getCar().getCategory().getEname()+"\t"+record.getStart_date()+"\t"+record.getReturn_date());
		}
		return list;
		
	}


}

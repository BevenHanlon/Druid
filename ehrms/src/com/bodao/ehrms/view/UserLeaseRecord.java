package com.bodao.ehrms.view;

import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Record;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * 用户查询租赁记录界面
 * @author 59112
 *
 */
public class UserLeaseRecord extends Client {
	public List<Record> UserLeaseRecord(int user_id){
		Scanner sc = new Scanner(System.in);
		String url = URLPathUtil.FIND_ONE_USER+"?user_id="+user_id;
		String req=request(url);
		System.out.println("==================================================");
		System.out.println("编号"+"\t汽车编号"+"\t汽车名称"+"\t\t租金总额"+"\t\t备注"+"\t\t品牌"+"\t\t类型"+"\t\t借车时间"+"\t\t还车时间");
		List<Record> list =JSON.parseObject(req, new TypeReference<List<Record>>(){}.getType());
		for (Record record : list) {		
			System.out.println(record.getId()+"\t\t"+record.getCar().getId()+"\t\t\t"+record.getCar().getModel()+"\t\t\t"+record.getPayment()+"/元"+"\t\t"+record.getCar().getT_comments()
					+"\t\t"+record.getCar().getBrand().getSname()+"\t\t"+record.getCar().getCategory().getEname()+"\t\t"+record.getStart_date()+"\t"+record.getReturn_date());
		}
		return list;
	}
	
}

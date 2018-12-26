package com.bodao.ehrms.view;

import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Record;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * 用户租赁界面
 * @author 59112
 *
 */
public class UserLease extends Client {
	public void lease(int user_id){
		Scanner sc = new Scanner(System.in);
		System.out.println("==================");
		System.out.println("输入0退出");
		System.out.println("输入 1进入选择汽车 ");	
		int choose = sc.nextInt();
		switch (choose) {
		case 0:
			return;
		case 1:
			System.out.println("请输入汽车编号选择你要租的车辆:");
			int id =sc.nextInt();
			String url1=URLPathUtil.USER_LEASE_CAR+"?car_id="+id+"&user_id="+user_id;
            String req=request(url1);	
			Record record = JSON.parseObject(req, new TypeReference<Record>(){}.getType());
			if(record!=null) {
				System.out.println("借车成功！"+"租车信息如下：");
				System.out.println("编号"+"\t汽车名称"+"\t每日租金"+"\t  备注"+"\t\t品牌"+"\t\t  类型"+"\t\t  借车时间");
				System.out.println(record.getId()+"\t\t"+record.getCar().getModel()+"\t\t"+record.getCar().getRent()+"/天"+"\t"+record.getCar().getT_comments()+"\t"+record.getCar().getBrand().getSname()+"\t\t"+record.getCar().getCategory().getEname()+"\t"+record.getStart_date());
				
			}else {
				System.out.println("借车失败");		
			}
			break;
		default:
			System.out.println("输入不正确，请重新输入！");
			break;
			}
		}

	}


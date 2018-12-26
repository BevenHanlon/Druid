package com.bodao.ehrms.view;

import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Record;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * ��������
 * @author 59112
 *
 */
public class UserReturnCar extends Client{
	public void ReturnCar(int id){
		Scanner sc = new Scanner(System.in);
		System.out.println("==================");
		System.out.println("����Ҫ�����������");
		int car_id=sc.nextInt();
		String url=URLPathUtil.USER_RETURN_CAR+"?car_id="+car_id+"&user_id="+id;
		String req =request(url);
		Record record = JSON.parseObject(req, new TypeReference<Record>(){}.getType());
		System.out.println("�����ɹ���"+"������Ϣ���£�");
		System.out.println("���"+"\t��������"+"\tÿ�����"+"\t�����"+"\t\t  ��ע"+"\t\tƷ��"+"\t\t����"+"\t\t  �賵ʱ��"+"\t\t  ����ʱ��");
		System.out.println(record.getId()+"\t\t"+record.getCar().getModel()+"\t\t "+record.getCar().getRent()+"/��"+"\t"+record.getPayment()+"/Ԫ"+"\t\t"+record.getCar().getT_comments()+"\t"+record.getCar().getBrand().getSname()+"\t\t"+record.getCar().getCategory().getEname()+"\t"+record.getStart_date()+"\t"+record.getReturn_date());
			
		}

}

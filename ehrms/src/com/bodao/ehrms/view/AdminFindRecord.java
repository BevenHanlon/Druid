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
 * ����Ա�鿴������¼���棬Ĭ�ϲ鿴ȫ�����޼�¼
 * @author 59112
 *
 */
public class AdminFindRecord extends Client{
	public List<Record> AdminFindRecord(){
		Scanner sc = new Scanner(System.in);
		String url =URLPathUtil.FIND_ALL_RECORD;
		String req =request(url);
		System.out.println("===========================================================================");
		System.out.println("���"+"\t�������"+"\t��������"+"\t�û����"+"\t�û���"+"\tÿ�����"+"\t����ܶ�"+"\t  ��ע"+"\t\tƷ��"+"\t\t����"+"\t\t  �賵ʱ��"+"\t\t ����ʱ��");
		List<Record> list =JSON.parseObject(req,new TypeReference<List<Record>>(){}.getType());
		for (Record record : list) {
			System.out.println(record.getId()+"\t  "+record.getCar().getId()+"\t\t  "+record.getCar().getBrand().getSname()+"\t\t  "+record.getUser().getId()
					+"\t\t"+record.getUser().getName()+"\t\t"+record.getCar().getRent()+"/��"+"\t"+record.getPayment()+"/Ԫ"+"\t"+record.getCar().getT_comments()
					+"\t"+record.getCar().getBrand().getSname()+"\t\t"+record.getCar().getCategory().getEname()+"\t"+record.getStart_date()+"\t"+record.getReturn_date());
		}
		return list;
		
	}


}

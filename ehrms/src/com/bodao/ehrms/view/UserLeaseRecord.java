package com.bodao.ehrms.view;

import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Record;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * �û���ѯ���޼�¼����
 * @author 59112
 *
 */
public class UserLeaseRecord extends Client {
	public List<Record> UserLeaseRecord(int user_id){
		Scanner sc = new Scanner(System.in);
		String url = URLPathUtil.FIND_ONE_USER+"?user_id="+user_id;
		String req=request(url);
		System.out.println("==================================================");
		System.out.println("���"+"\t�������"+"\t��������"+"\t\t����ܶ�"+"\t\t��ע"+"\t\tƷ��"+"\t\t����"+"\t\t�賵ʱ��"+"\t\t����ʱ��");
		List<Record> list =JSON.parseObject(req, new TypeReference<List<Record>>(){}.getType());
		for (Record record : list) {		
			System.out.println(record.getId()+"\t\t"+record.getCar().getId()+"\t\t\t"+record.getCar().getModel()+"\t\t\t"+record.getPayment()+"/Ԫ"+"\t\t"+record.getCar().getT_comments()
					+"\t\t"+record.getCar().getBrand().getSname()+"\t\t"+record.getCar().getCategory().getEname()+"\t\t"+record.getStart_date()+"\t"+record.getReturn_date());
		}
		return list;
	}
	
}

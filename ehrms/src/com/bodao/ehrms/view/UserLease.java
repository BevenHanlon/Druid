package com.bodao.ehrms.view;

import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Record;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * �û����޽���
 * @author 59112
 *
 */
public class UserLease extends Client {
	public void lease(int user_id){
		Scanner sc = new Scanner(System.in);
		System.out.println("==================");
		System.out.println("����0�˳�");
		System.out.println("���� 1����ѡ������ ");	
		int choose = sc.nextInt();
		switch (choose) {
		case 0:
			return;
		case 1:
			System.out.println("�������������ѡ����Ҫ��ĳ���:");
			int id =sc.nextInt();
			String url1=URLPathUtil.USER_LEASE_CAR+"?car_id="+id+"&user_id="+user_id;
            String req=request(url1);	
			Record record = JSON.parseObject(req, new TypeReference<Record>(){}.getType());
			if(record!=null) {
				System.out.println("�賵�ɹ���"+"�⳵��Ϣ���£�");
				System.out.println("���"+"\t��������"+"\tÿ�����"+"\t  ��ע"+"\t\tƷ��"+"\t\t  ����"+"\t\t  �賵ʱ��");
				System.out.println(record.getId()+"\t\t"+record.getCar().getModel()+"\t\t"+record.getCar().getRent()+"/��"+"\t"+record.getCar().getT_comments()+"\t"+record.getCar().getBrand().getSname()+"\t\t"+record.getCar().getCategory().getEname()+"\t"+record.getStart_date());
				
			}else {
				System.out.println("�賵ʧ��");		
			}
			break;
		default:
			System.out.println("���벻��ȷ�����������룡");
			break;
			}
		}

	}


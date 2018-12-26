package com.bodao.ehrms.view;

import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * ��ѯָ��������Ϣ
 * @author 59112
 *
 */
public class AdminFindOneCar extends Client {
	public void findone(){
		Scanner sc =new Scanner(System.in);
		System.out.println("===========================");
		System.out.println("������ָ��������ţ�");
		int id = sc.nextInt();
		String url=URLPathUtil.FIND_ONE_CAR+"?id="+id;
		String response = request(url);//���ϱ��json�ַ���
		Car car = JSON.parseObject(response,new TypeReference<Car>(){}.getType());
		System.out.println("���"+"\t��������"+"\t\t��ע"+"\t\tƷ��"+"\t\t����"+"\t\t�۸�"+"\t\t�Ƿ����"+"\t�Ƿ��ϼ�");
		System.out.println(car.getId()+"\t"+car.getModel()+"\t\t    "+car.getT_comments()+"\t\t"+car.getBrand().getSname()+"\t\t"+car.getCategory().getEname()+"\t"+car.getRent()+"/��"+"\t  "+(car.getStatu()==0?"����":"������")+"\t\t"+(car.getUseable()==0?"�ϼ�":"�¼�"));
	}
	
}

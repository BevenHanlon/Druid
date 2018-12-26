package com.bodao.ehrms.view;

import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Car;
/**
 * ����Ա��ѯ����
 * @author 59112
 *
 */
public class AdminFindCar extends Client {
	public List<Car> AdminFindCar(){
		Scanner sc = new Scanner(System.in);
		System.out.println("======================================================");
		System.out.println("���"+"\t��������"+"\t\t��ע"+"\t\tƷ��"+"\t\t����"+"\t\t�۸�"+"\t\t�Ƿ����"+"\t�Ƿ��ϼ�");
		String url="findallcar";
		String response = request(url);//���ϱ��json�ַ���
		List<Car> list = JSON.parseObject(response,new TypeReference<List<Car>>(){}.getType());
		for (Car car : list) {
			System.out.println(car.getId()+"\t"+car.getModel()+"\t\t    "+car.getT_comments()+"\t\t"+car.getBrand().getSname()+"\t\t"+car.getCategory().getEname()+"\t"+car.getRent()+"/��"+"\t  "+(car.getStatu()==0?"����":"������")+"\t\t"+(car.getUseable()==0?"�ϼ�":"�¼�"));
		}
		return list;

	}

}

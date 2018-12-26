package com.bodao.ehrms.view;

import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.Car;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * ��������
 * @author 59112
 *
 */
public class UserFindCategory extends Client{
		public List<Car> findcategory(){
			Scanner sc = new Scanner(System.in);
			System.out.println("���������ͱ�ţ�1.������2.��Ӣ��3.������4.������");
			int cid = sc.nextInt();
			String url =URLPathUtil.FIND_ONE_CATEGORY+"?cid="+cid;
			String req =request(url);
			System.out.println("=========================================");
			System.out.println("���"+"\t��������"+"\t\t��ע"+"\t\tƷ��"+"\t\t  ����"+"\t\t  �۸�"+"\t\t�Ƿ����");
			List<Car> list = JSON.parseObject(req,new TypeReference<List<Car>>(){}.getType());
			for (Car car : list) {
				System.out.println(car.getId()+"\t\t"+car.getModel()+"\t\t\t"+car.getT_comments()+"\t\t\t"+car.getBrand().getSname()+"\t\t\t"+car.getCategory().getEname()+"\t\t"+car.getRent()+"/��"+"\t\t"+(car.getStatu()==0?"����":"������"));
			}
			return list;
		}

	
}

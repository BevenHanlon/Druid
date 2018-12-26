package com.bodao.ehrms.view;

import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.User;
import com.bodao.ehrms.util.URLPathUtil;
/**
 * ����Ա��¼view
 * @author 59112
 *
 */
public class AdminLoginView extends Client{
	Scanner sc = new Scanner(System.in);
	AdminFindCar find = new AdminFindCar();
	AdminFindOneCar findone = new AdminFindOneCar();
	AdminAddCar add = new AdminAddCar();
	AdminUpdateCar update = new AdminUpdateCar();
	AdminFindRecord fr = new AdminFindRecord();
	public  void AdminLoginView() {
		System.out.println("===============");
		System.out.println("��ӭ���ʶ����⳵");
		System.out.println("===============");
		System.out.println("1.��¼ 2.�˳�");
		while (true) {
			int choose = sc.nextInt();
			switch (choose) {
			case 1:
				System.out.println("=================��¼==================>>>>");
				System.out.println("�û���:");
				String username =sc.next();
				System.out.println("����:");
				String pwd = sc.next();
				//���ù���Ա��¼����
				String url =URLPathUtil.LOGIN+"?username="+username+"&pwd="+pwd+"&type=1";
				String req=request(url);
				User user = JSON.parseObject(req, new TypeReference<User>(){}.getType());
				//�ж��Ƿ��¼�ɹ�
				if (user!=null) {
					System.out.println("��ӭ"+user.getName()+"��¼");
					find.AdminFindCar();
					option();
					break;
				}else{
					System.out.println("�û���������������������룡");				
				} 
				break;
			case 2://�˳�
				System.exit(0);
				break;
			default:
				System.out.println("���벻��ȷ,����������!");
				option();
				break;
			}
		}
	}
	private void option() {
		System.out.println("========================================================");
		System.out.println("����0"+"�˳�");
		System.out.println("����1"+"��ѯָ������");
		System.out.println("����5��ѯȫ��������");
		System.out.println("����6"+"�������");
		System.out.println("����7"+"�޸�������Ϣ");
		System.out.println("����8"+"�鿴�������޼�¼");
		int cho = sc.nextInt();
		switch (cho) {
		case 0:
			System.exit(0);
			break;
		case 1:
			findone.findone();
			option();
			break;
		case 5:
			find.AdminFindCar();
			option();
			break;
		case 6:
			add.AddCar();
			option();
			break;
		case 7:
			update.UpdateCar();
			option();
			break;
		case 8:
			fr.AdminFindRecord();
			option();
			break;
		default:
			System.out.println("���벻��ȷ,����������!");
			break;
		}
	}



	
}

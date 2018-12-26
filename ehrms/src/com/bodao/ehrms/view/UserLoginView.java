package com.bodao.ehrms.view;

import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.User;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * �û���¼view
 * 
 * @author 59112
 *
 */
public class UserLoginView extends Client {


	Scanner sc = new Scanner(System.in);
	UserFindCar find = new UserFindCar();
	UserFindCategory fc = new UserFindCategory();
	UserFindBrand fb = new UserFindBrand();
	UserLease ul = new UserLease();
	UserLeaseRecord ulr = new UserLeaseRecord();
	SortCar sort = new SortCar();
	UserReturnCar rc = new UserReturnCar();
	public void start() {
		System.out.println("=====��¼=====>>>>");
		while (true) {
			System.out.println("�������û���:");
			String name = sc.next();
			System.out.println("����������:");
			String pwd = sc.next();
			String url = URLPathUtil.LOGIN + "?username=" + name + "&pwd=" + pwd + "&type=0";
			String req = request(url);
			User user = JSON.parseObject(req, new TypeReference<User>() {
			}.getType());
			if (user != null) {
				System.out.println("��ӭ" + user.getName());
				find.UserFindCar();
				while (true) {
					userOption(sc, fc, fb, ul, ulr, sort, rc, user);
				}
			} else {
				System.out.println("�û��������������");

			}
		}

	}

	private void userOption(Scanner sc, UserFindCategory fc, UserFindBrand fb, UserLease ul, UserLeaseRecord ulr,
			SortCar sort, UserReturnCar rc, User user) {
		System.out.println("============================================");
		System.out.println("����0�˳�");
		System.out.println("����1 �����⳵����");
		System.out.println("����2����");
		System.out.println("����3����������");
		System.out.println("����4��Ʒ������");
		System.out.println("����5��ѯȫ��������Ϣ");
		System.out.println("����6 �鿴�ҵ��⳵��¼");
		System.out.println("����7 ���� ����");
		int choose = sc.nextInt();
		switch (choose) {
		case 0:
			System.exit(0);
		case 1:
			ul.lease(user.getId());
			break;
		case 2:
			sort.sort();
			break;
		case 3:
			fc.findcategory();
			break;
		case 4:
			fb.FindBrand();
			break;
		case 5:
			find.UserFindCar();
			break;
		case 6:
			ulr.UserLeaseRecord(user.getId());
			break;
		case 7:
			rc.ReturnCar(user.getId());
			break;
		default:
			System.out.println("����������������룡");
			break;
		}
	}

}

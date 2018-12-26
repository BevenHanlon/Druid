package com.bodao.ehrms.view;

import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.User;
import com.bodao.ehrms.util.URLPathUtil;

/**
 * 用户登录view
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
		System.out.println("=====登录=====>>>>");
		while (true) {
			System.out.println("请输入用户名:");
			String name = sc.next();
			System.out.println("请输入密码:");
			String pwd = sc.next();
			String url = URLPathUtil.LOGIN + "?username=" + name + "&pwd=" + pwd + "&type=0";
			String req = request(url);
			User user = JSON.parseObject(req, new TypeReference<User>() {
			}.getType());
			if (user != null) {
				System.out.println("欢迎" + user.getName());
				find.UserFindCar();
				while (true) {
					userOption(sc, fc, fb, ul, ulr, sort, rc, user);
				}
			} else {
				System.out.println("用户名或者密码错误！");

			}
		}

	}

	private void userOption(Scanner sc, UserFindCategory fc, UserFindBrand fb, UserLease ul, UserLeaseRecord ulr,
			SortCar sort, UserReturnCar rc, User user) {
		System.out.println("============================================");
		System.out.println("输入0退出");
		System.out.println("输入1 进入租车订单");
		System.out.println("输入2排序");
		System.out.println("输入3按类型搜索");
		System.out.println("输入4按品牌搜索");
		System.out.println("输入5查询全部汽车信息");
		System.out.println("输入6 查看我的租车记录");
		System.out.println("输入7 进入 还车");
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
			System.out.println("输入错误请重新输入！");
			break;
		}
	}

}

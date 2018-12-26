package com.bodao.ehrms.view;

import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.User;
import com.bodao.ehrms.util.URLPathUtil;
/**
 * 管理员登录view
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
		System.out.println("欢迎访问二嗨租车");
		System.out.println("===============");
		System.out.println("1.登录 2.退出");
		while (true) {
			int choose = sc.nextInt();
			switch (choose) {
			case 1:
				System.out.println("=================登录==================>>>>");
				System.out.println("用户名:");
				String username =sc.next();
				System.out.println("密码:");
				String pwd = sc.next();
				//调用管理员登录方法
				String url =URLPathUtil.LOGIN+"?username="+username+"&pwd="+pwd+"&type=1";
				String req=request(url);
				User user = JSON.parseObject(req, new TypeReference<User>(){}.getType());
				//判断是否登录成功
				if (user!=null) {
					System.out.println("欢迎"+user.getName()+"登录");
					find.AdminFindCar();
					option();
					break;
				}else{
					System.out.println("用户名或密码错误，请重新输入！");				
				} 
				break;
			case 2://退出
				System.exit(0);
				break;
			default:
				System.out.println("输入不正确,请重新输入!");
				option();
				break;
			}
		}
	}
	private void option() {
		System.out.println("========================================================");
		System.out.println("输入0"+"退出");
		System.out.println("输入1"+"查询指定汽车");
		System.out.println("输入5查询全部汽车！");
		System.out.println("输入6"+"添加汽车");
		System.out.println("输入7"+"修改汽车信息");
		System.out.println("输入8"+"查看汽车租赁记录");
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
			System.out.println("输入不正确,请重新输入!");
			break;
		}
	}



	
}

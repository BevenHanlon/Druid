package com.bodao.ehrms.view;

import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.User;
import com.bodao.ehrms.util.URLPathUtil;
/**
 * 用户注册view
 * @author 59112
 *
 */
public class UserRigsterView extends Client{
	public boolean RigsterView() {
		Scanner sc = new Scanner(System.in);
		UserLoginView login = new UserLoginView();
		System.out.println("欢迎注册二嗨租车");	
		System.out.println("输入0.退出1.注册");
		int choose = sc.nextInt();
		switch (choose) {
		case 0:
			System.exit(0);
			break;
		case 1:
			System.out.println("请输入用户名:");
			String username = sc.next();
			System.out.println("请输入密码:");
			String pwd = sc.next();
			System.out.println("请输入性别:0.男1.女");
			int sex = sc.nextInt();
			System.out.println("请输入身份证号码:");
			String id_card = sc.next();
			System.out.println("请输入手机号码:");
			String tel = sc.next();
			System.out.println("请输入家庭住址:");
			String addr = sc.next();
			String url =URLPathUtil.REGISTER+"?username="+username+"&pwd="+pwd+"&sex="+sex+"&id_card="+id_card+"&tel="+tel+"&addr="+addr;
			String req = request(url);	
			if (req.equals("1")) {
				System.out.println("注册成功！");
				login.start();
				return true;
			}else{
				System.out.println("注册失败！");
			}
			break;
		default:
			break;
		}
		return false;
	}
	
}

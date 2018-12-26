package com.bodao.ehrms.main;

import java.util.Scanner;

import com.bodao.ehrms.view.AdminLoginView;
import com.bodao.ehrms.view.UserLoginView;
import com.bodao.ehrms.view.UserRigsterView;

/**
 * 程序入口
 * @author 59112
 *
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserLoginView  ulv = new UserLoginView();
		AdminLoginView alv = new AdminLoginView();
		UserRigsterView rv = new UserRigsterView();
		System.out.println("==============================");
		System.out.println("欢迎访问二嗨租车！");
		System.out.println("1.登录2.注册3.退出");
		while (true) {
			int choose = sc.nextInt();
			switch (choose) {
			case 1://登录
				System.out.println("1.用户登录2.管理员登录");
				int cho =sc.nextInt();
			 if (cho==1) {
					//用户登录
					ulv.start();
				}else{
					//管理员登录
					alv.AdminLoginView();
				}
				break;
			case 2://注册
			rv.RigsterView();			
				break;
			case 3:
				System.exit(0);
			default:
				System.out.println("输入错误请重新输入！");
				break;
			}
			
		}
		
	}
}

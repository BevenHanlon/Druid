package com.bodao.ehrms.main;

import java.util.Scanner;

import com.bodao.ehrms.view.AdminLoginView;
import com.bodao.ehrms.view.UserLoginView;
import com.bodao.ehrms.view.UserRigsterView;

/**
 * �������
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
		System.out.println("��ӭ���ʶ����⳵��");
		System.out.println("1.��¼2.ע��3.�˳�");
		while (true) {
			int choose = sc.nextInt();
			switch (choose) {
			case 1://��¼
				System.out.println("1.�û���¼2.����Ա��¼");
				int cho =sc.nextInt();
			 if (cho==1) {
					//�û���¼
					ulv.start();
				}else{
					//����Ա��¼
					alv.AdminLoginView();
				}
				break;
			case 2://ע��
			rv.RigsterView();			
				break;
			case 3:
				System.exit(0);
			default:
				System.out.println("����������������룡");
				break;
			}
			
		}
		
	}
}

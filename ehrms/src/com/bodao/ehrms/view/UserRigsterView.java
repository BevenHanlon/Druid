package com.bodao.ehrms.view;

import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bodao.ehrms.client.Client;
import com.bodao.ehrms.entity.User;
import com.bodao.ehrms.util.URLPathUtil;
/**
 * �û�ע��view
 * @author 59112
 *
 */
public class UserRigsterView extends Client{
	public boolean RigsterView() {
		Scanner sc = new Scanner(System.in);
		UserLoginView login = new UserLoginView();
		System.out.println("��ӭע������⳵");	
		System.out.println("����0.�˳�1.ע��");
		int choose = sc.nextInt();
		switch (choose) {
		case 0:
			System.exit(0);
			break;
		case 1:
			System.out.println("�������û���:");
			String username = sc.next();
			System.out.println("����������:");
			String pwd = sc.next();
			System.out.println("�������Ա�:0.��1.Ů");
			int sex = sc.nextInt();
			System.out.println("���������֤����:");
			String id_card = sc.next();
			System.out.println("�������ֻ�����:");
			String tel = sc.next();
			System.out.println("�������ͥסַ:");
			String addr = sc.next();
			String url =URLPathUtil.REGISTER+"?username="+username+"&pwd="+pwd+"&sex="+sex+"&id_card="+id_card+"&tel="+tel+"&addr="+addr;
			String req = request(url);	
			if (req.equals("1")) {
				System.out.println("ע��ɹ���");
				login.start();
				return true;
			}else{
				System.out.println("ע��ʧ�ܣ�");
			}
			break;
		default:
			break;
		}
		return false;
	}
	
}

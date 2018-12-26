package com.bodao.ehrms.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * �ͻ���
 * @author 59112
 *
 */
public class Client {
	Socket s;
	BufferedReader br;
	PrintWriter pw;

/**
 * �������������Ӧ
 * @param url
 * @return
 */
public String request(String url){
	/**
	 * ����һ��Socket�׽��ֶ�������һ�������IP�Ͷ˿ں�
  		���ӳɹ�
  		ͨ��IO�������պͷ���������
	 * @param args
	 */
	String response = null;
	try {
		s = new Socket("localhost", 8099);
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		pw = new PrintWriter(s.getOutputStream());
		pw.println(url);//д��ȥ
		pw.flush();
		response = br.readLine();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return response;
	}
}

package com.bodao.ehrms.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * �����
 * @author 59112
 *
 */
public class Server {
	/**
	 * �����
  		����һ��ServerSocket�׽��ֶ���, ����һ���˿ں�
 		���տͻ��˷��������󣬷���һ���׽��ֶ���
  		ͨ��IO�������պͷ�������
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8099);
			while(true){
				System.out.println("����������...");
				Socket s = ss.accept();
				System.out.println("�ͻ������ӳɹ�");
				//����һ�߳�
			   Thread  t = new Thread( new ServerHandlerRequest(s));
			   t.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



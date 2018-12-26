package com.bodao.ehrms.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 客户端
 * @author 59112
 *
 */
public class Client {
	Socket s;
	BufferedReader br;
	PrintWriter pw;

/**
 * 发送请求接收响应
 * @param url
 * @return
 */
public String request(String url){
	/**
	 * 创建一个Socket套接字对象，连接一个服务端IP和端口号
  		连接成功
  		通过IO操作接收和发送数据了
	 * @param args
	 */
	String response = null;
	try {
		s = new Socket("localhost", 8099);
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		pw = new PrintWriter(s.getOutputStream());
		pw.println(url);//写出去
		pw.flush();
		response = br.readLine();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return response;
	}
}

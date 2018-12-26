package com.bodao.ehrms.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 服务端
 * @author 59112
 *
 */
public class Server {
	/**
	 * 服务端
  		创建一个ServerSocket套接字对象, 监听一个端口号
 		接收客户端发来的请求，返回一个套接字对象
  		通过IO操作接收和发送数据
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8099);
			while(true){
				System.out.println("服务器开启...");
				Socket s = ss.accept();
				System.out.println("客户端连接成功");
				//传给一线程
			   Thread  t = new Thread( new ServerHandlerRequest(s));
			   t.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



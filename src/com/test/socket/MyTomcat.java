package com.test.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyTomcat {

	public static void main(String[] args) {
		try {
			//1、新建socket服务端
			ServerSocket tomcat = new ServerSocket(9090);
			System.out.println("服务器启动");
			//2、服务端监听
			Socket s = tomcat.accept();
			//3、打印接受到的流信息
			//设置缓冲池
			byte[] buf=new byte[1024];
			//获得输入流
			InputStream in = s.getInputStream();
			int length=0;
			StringBuffer request=new StringBuffer();
			while((length=in.read(buf))!=-1){
				String line = new String(buf,0,length);
				request.append(line);
			}
			System.out.println("request:"+request);
			 PrintWriter pw = new PrintWriter(s.getOutputStream(),true);  
	            pw.println("<html>");  
	            pw.println("<head>");  
	            pw.println("<title>LiveSession List</title>");  
	            pw.println("</head>");  
	            pw.println("<body>");  
	            pw.println("<p style=\"font-weight: bold;color: red;\">welcome to MyTomcat</p>");  
	            pw.println("</body>");  
	            s.close();  
	            tomcat.close();  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

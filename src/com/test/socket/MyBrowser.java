package com.test.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MyBrowser {

	public static void main(String[] args) {
		try {
			//发送请求
			//1、创建socket请求对象（请求地址和端口）
			Socket browser = new Socket("192.168.1.104",9090);
			//2、创建输出流
			PrintWriter pw = new PrintWriter(browser.getOutputStream(),true);
			//3、封装请求行
			pw.println("GET / HTTP/1.1");
			pw.println("User-Agent: Java/1.7.0_1");
			pw.println("Host: 192.168.1.102:9090");
			//4、封装请求头
			pw.println("Connection: keep-alive");
			pw.println("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
			pw.println("Upgrade-Insecure-Requests: 1");
			pw.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			pw.println("Accept-Encoding: gzip, deflate, br");
			pw.println("Accept-Language: zh-CN,zh;q=0.9");
			//5、空行
			pw.println();
			//6、封装请求体
            pw.println("UserName=zhangsan&Age=17");  
            //7、关闭输出流
			browser.shutdownOutput();
			
			
			//获取返回信息
			//1 打开输入流
			InputStream in = browser.getInputStream();
			//2 输入流转换为字符串
			byte[] buf =new byte[1024];
			StringBuilder response=new StringBuilder();
			int length=0;
			while((length=in.read(buf))!=-1){
					String line = new String(buf,0,length);
					response.append(line);
			}
			System.out.println(response);
			 //browser.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
	}

}

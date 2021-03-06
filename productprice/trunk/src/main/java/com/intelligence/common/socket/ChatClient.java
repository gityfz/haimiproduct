package com.intelligence.common.socket;

import java.net.*;
import java.io.*;


public class ChatClient{
 private DatagramSocket s;
 private InetAddress hostAddress;
 private byte[] buf = new byte[1000];
 private DatagramPacket dp = new DatagramPacket(buf,buf.length);
//方法说明：构造器，这里实现接收用户输入和与服务器通讯
  public ChatClient(){
   try{
       //使用构造器，创建使用本机任何可用端口的数据包Socket
       s = new DatagramSocket();
       //获取本地IP
       hostAddress = InetAddress.getByName("localhost");
       while(true){
         String outMessage ="";  
        //读取输入
         BufferedReader stdin  = new BufferedReader(new InputStreamReader(System.in));
         try{
           outMessage = stdin.readLine();
         }catch(IOException ie){  System.err.println("IO error!");    }
         //如果输入“bye”则表示退出程序
         if(outMessage.equals("bye")) break;
         String outString = "Client say: "+ outMessage;
         byte[] buf = outString.getBytes();
         //打包数据，发送数据
         DatagramPacket out = new DatagramPacket(buf,buf.length,hostAddress,4000);
         s.send(out);
         //等待服务器返回
         s.receive(dp);
         String rcvd = "rcvd from "+ dp.getAddress() + ", " + dp.getPort() + 
         ": "+ new String(dp.getData(),0,dp.getLength());
         System.out.println(rcvd);
        }
     }catch(UnknownHostException e){
       System.out.println("Can;t open socket");
       System.exit(1);
     }catch(SocketException e){
       System.out.println("Can;t open socket");
       e.printStackTrace();
       System.exit(1);
     }catch(IOException e){
       System.err.println("Communication error");
       e.printStackTrace();
       System.exit(1);
     }catch(Exception e){
       System.err.println("Communication error");
       e.printStackTrace();
       System.exit(1);
     }
     System.out.println("ChatClient over");
 }
public static void main(String[] args){    
	new ChatClient(); 
	}
}
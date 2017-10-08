package com.intelligence.common.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Stream;

public class Test {

//	public static void main(String[] args) throws IOException {
//		try {
//			ServerSocket server = null;
//			try {
//				server = new ServerSocket(6678);
//			} catch (Exception e) {
//				System.out.println("can not listen to:" + e);
//			}
//			Socket socket = null;
//			try {
//				socket = server.accept();
//			} catch (Exception e) {
//				System.out.println("Error." + e);
//			}
//			String line;
//			BufferedReader is = new BufferedReader(new InputStreamReader(
//					socket.getInputStream()));
//			PrintWriter os = new PrintWriter(socket.getOutputStream());
////			BufferedReader sin = new BufferedReader(new InputStreamReader(
////					System.in));
////			System.out.println("Client:" + is.readLine());
////			line = sin.readLine();
//			line = is.readLine();
//			while (null != line && !line.equals("bye")) {
//				os.println(line);
//				os.flush();
//				System.out.println("Client:" + line);
//				line = is.readLine();
//			}
//			os.close();
//			is.close();
//			socket.close();
//			server.close();
//		} catch (Exception e) {
//			System.out.println("Error:" + e);
//		}
//	}
	
	  public static void main(String[] args) throws IOException {
	        ServerSocket server = new ServerSocket(6678);  
	          
	        while (true) {  
	            Socket socket = server.accept();  
	            invoke(socket);  
	        }  
	    }  
	
	 private static void invoke(final Socket client) throws IOException {  
	        new Thread(new Runnable() {  
	            public void run() {  
	                BufferedReader in = null;  
	                PrintWriter out = null;  
	                try {  
	                    in = new BufferedReader(new InputStreamReader(client.getInputStream()));  
	                    out = new PrintWriter(client.getOutputStream());
//	                    String msg = in.readLine();
	                    int ascii = in.read();
	                    Stream<String> aaa = in.lines();
	                    System.out.println(aaa.toString());
	                    while (-1 != ascii && true) {
	                    	System.out.println(ascii);
	                        System.out.println("Server received " + (char)Integer.parseInt(String.valueOf(ascii)));
	                        out.println("Server received " + (char)Integer.parseInt(String.valueOf(ascii)));  
	                        out.flush();
//	                        msg = in.readLine();
//	                        msg = in.read();
	                        ascii = in.read();
	                    }  
	                } catch(IOException ex) {  
	                    ex.printStackTrace();  
	                } finally {  
	                    try {  
	                        in.close();  
	                    } catch (Exception e) {}  
	                    try {  
	                        out.close();  
	                    } catch (Exception e) {}  
	                    try {  
	                        client.close();  
	                    } catch (Exception e) {}  
	                }  
	            }  
	        }).start();  
	    }  

}

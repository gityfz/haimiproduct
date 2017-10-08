package com.intelligence.common.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadTxt {

	 
		    public static void main(String[] args) {
		        try {
		        	StringBuffer sb = new StringBuffer("");
		        	int count = 0;
		        	 BufferedReader br = new BufferedReader(new InputStreamReader(
		     		        new FileInputStream("D://ls.txt")));
		     		        for (String line = br.readLine(); line != null; line = br.readLine()) {
		     		        	count++;
//		     		                           System.out.println(line);
		     		                          sb.append(line);
		     		                         sb.append(",");
		     		               if (0 == count % 30) {
		     		            	  sb.append("\n");
		     		               }

		     		        }
		     		        br.close();
		     		       System.out.println(sb);
		        } catch (IOException e1) {
		            e1.printStackTrace();
		            System.out.println("写入失败");
		            System.exit(-1);
		        }
		    }
		


		public static final void readF1(String filePath) throws IOException {      
		       
		    }
	
	
}

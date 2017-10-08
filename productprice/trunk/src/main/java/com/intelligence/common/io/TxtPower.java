package com.intelligence.common.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import com.intelligence.common.utils.NumUtils;

public class TxtPower {
	
	/**
	 * 写入TXT
	 * 
	 * @param url
	 * @param urlFlag
	 * @param content
	 * @throws IOException 
	 */
	public static void writerTxt(String url, Boolean urlFlag, StringBuffer content, String encode) throws IOException {
		// 初始化数据写入工具
		BufferedWriter fw = null;

		try {
			// 初始化文件路径
			String filePath = url;
			if (urlFlag) {
				filePath = FileUtils.getClassLoaderFileUrl(url);
			}
			
			// 初始化文件类
			File file = new File(filePath);

			// 获取文件写入流，指定编码格式，以免读取时中文字符异常
			fw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, true), encode));  
			// 添加需要写入的内容
			fw.append(content);
			// 全部写入缓存中的内容
			fw.flush(); 
		} catch (IOException e) {
			throw e;
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					throw e;
				} finally {
					fw = null;
				}
			}
		}
	}
	
	/**
	 * 读取TXT
	 * 
	 * @param url
	 * @param urlFlag
	 * @param content
	 * @throws IOException 
	 */
	public static LinkedHashMap<Integer, String> readTxt(String url, Boolean urlFlag, List<HashMap<String, Integer>> range, String encode) throws IOException {
		// 初始化返回结果
		LinkedHashMap<Integer, String> mapRe = new LinkedHashMap<Integer, String>();
		// 初始化读数据工具
		BufferedReader reader = null;
		
		try {
			// 初始化文件路径
			String filePath = url;
			if (urlFlag) {
				filePath = FileUtils.getClassLoaderFileUrl(url);
			}
			
			// 指定读取文件的编码格式，要和写入的格式一致，以免出现中文乱码
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath), encode)); 

			// 获取要读取的行范围
			Boolean allRead = false;
			HashSet<Integer> setNum = null;
			Integer start = null;
			Integer limit = null;
			
			// 判断是否是全面扫描
			if (null == range) {
				allRead = true;
			} else {
				setNum = new HashSet<Integer>();
				for (int i = 0, m = range.size(); i < m; i++) {
					HashMap<String, Integer> temp = range.get(i);
					if (!temp.containsKey("start") || !temp.containsKey("limit") || (0 > temp.get("start") && 0 > temp.get("limit"))) {
						throw new IOException();
					} else if (0 > temp.get("start")) {
						limit = temp.get("limit");
					} else if (0 > temp.get("limit")) {
						start = temp.get("start");
					} else {
						setNum.addAll(NumUtils.generateContinueNumNoSeq(temp.get("start"), temp.get("limit")));
					}
				}
			}
			
			// 根据范围再次判断是否全面扫描
			if (null != start && null != limit && start >= limit) {
				allRead = true;
			}
			
			// 初始化计数标记
			int countFlag = 0;
			// 初始化临时字符串
			String str = null;
			while ((str = reader.readLine()) != null) {
				countFlag++;
				if (allRead) {
					mapRe.put(countFlag, str);
				} else if (!allRead && ((null != start && countFlag >= start) || (null != limit && countFlag <= start) || setNum.contains(countFlag))) {
					mapRe.put(countFlag, str);
				}
			}

		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (null != reader) {
					reader.close();
				}
			} catch (IOException e) {
				throw e;
			} finally {
				reader = null;
			}

		}
		
		// 返回結果
		return mapRe;
	}

}

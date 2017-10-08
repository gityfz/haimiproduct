package com.intelligence.common.io;

import java.io.InputStream;

public final class FileUtils {

	/**
	 * 获取类文件加载方式路径
	 * 
	 * @param url
	 * @return
	 */
	public static String getClassFileUrl(String url) {
		return Object.class.getResource(url).getFile();
	}
	
	/**
	 * 获取类加载器文件加载方式路径
	 * 
	 * @param url
	 * @return
	 */
	public static String getClassLoaderFileUrl(String url) {
		return ClassLoader.class.getResource(url).getFile();
	}
	
	/**
	 * 获取类文件加载方式输入流
	 * 
	 * @param url
	 * @return
	 */
	public static InputStream getClassStream(String url) {
		return Object.class.getResourceAsStream(url);
	}
	
	/**
	 * 获取类加载器文件加载方式输入流
	 * 
	 * @param url
	 * @return
	 */
	public static InputStream getClassLoaderStream(String url) {
		return ClassLoader.class.getResourceAsStream(url);
	}
	
}

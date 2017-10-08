package com.intelligence.common.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Properties;

public class PropertyPower implements Serializable {

	/**
	 * 生成的序列号
	 */
	private static final long serialVersionUID = -6899850015200976973L;
	
	/**
	 * 类方式
	 */
	private static final String CLASS_WAY = "CLASS_WAY";
	
	/**
	 * 类加载器方式
	 */
	private static final String CLASS_LOADER_WAY = "CLASS_LOADER_WAY";
	
	/**
	 * 文件流方式
	 */
	private static final String FILE_WAY = "FILE_WAY";

	/**
	 * 配置文件对象
	 */
	private Properties prop = null;
	
	/**
	 * 文件地址
	 */
	private String propUrl = null;
	
	/**
	 * 加载方式
	 */
	private String LOAD_WAY = null;

	/**
	 * 根据class获取配置文件流  要带斜杠
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public Properties getPropertyByClassStream(String url) throws IOException {
		// 如果已经初始化，则抛异常，只允许初始化一次
		if (this.propUrl != null) {
			throw new IOException();
		}
		// 获取文件流
		InputStream in = FileUtils.getClassStream(url);
		// 初始化加载方式
		this.LOAD_WAY = CLASS_WAY;
		try {
			// 获取文件
			getPropCom(url, in);
		} catch (IOException e) {
			throw e;
		} finally {
			// 关闭流
			try {
				in.close();
			} catch (IOException e) {
				throw e;
			} finally {
				in = null;
			}
		}
		return this.prop;
	}
	
	/**
	 * 根据classLoader获取配置文件流  不要带斜杠
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public Properties getPropertyByClassloaderStream(String url) throws IOException {
		// 如果已经初始化，则抛异常，只允许初始化一次
		if (this.propUrl != null) {
			throw new IOException();
		}
		// 获取文件流
		InputStream in = FileUtils.getClassLoaderStream(url);
		// 初始化加载方式
		this.LOAD_WAY = CLASS_LOADER_WAY;
		try {
			// 获取文件
			getPropCom(url, in);
		} catch (IOException e) {
			throw e;
		} finally {
			// 关闭流
			try {
				in.close();
			} catch (IOException e) {
				throw e;
			} finally {
				in = null;
			}
		}
		return this.prop;
	}
	
	/**
	 * 根据class文件加载方式获取配置文件流  要带斜杠
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public Properties getPropertyByClassFile(String url) throws IOException {
		// 如果已经初始化，则抛异常，只允许初始化一次
		if (this.propUrl != null) {
			throw new IOException();
		}
		// 获取文件流
		InputStream in = new FileInputStream(FileUtils.getClassFileUrl(url));
		// 初始化加载方式
		this.LOAD_WAY = CLASS_WAY;
		try {
			// 获取文件
			getPropCom(url, in);
		} catch (IOException e) {
			throw e;
		} finally {
			// 关闭流
			try {
				in.close();
			} catch (IOException e) {
				throw e;
			} finally {
				in = null;
			}
		}
		return this.prop;
	}
	
	/**
	 * 根据classLoader文件加载方式获取配置文件流  不要带斜杠
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public Properties getPropertyByClassloaderFile(String url) throws IOException {
		// 如果已经初始化，则抛异常，只允许初始化一次
		if (this.propUrl != null) {
			throw new IOException();
		}
		// 获取文件流
		InputStream in = new FileInputStream(FileUtils.getClassLoaderFileUrl(url));
		// 初始化加载方式
		this.LOAD_WAY = CLASS_LOADER_WAY;
		try {
			// 获取文件
			getPropCom(url, in);
		} catch (IOException e) {
			throw e;
		} finally {
			// 关闭流
			try {
				in.close();
			} catch (IOException e) {
				throw e;
			} finally {
				in = null;
			}
		}
		return this.prop;
	}
	
	/**
	 * 根据文件加载方式获取配置文件流  标准文件路径
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public Properties getPropertyByFile(String url) throws IOException {
		// 如果已经初始化，则抛异常，只允许初始化一次
		if (this.propUrl != null) {
			throw new IOException();
		}
		// 获取文件流
		InputStream in = new FileInputStream(url);
		// 初始化加载方式
		this.LOAD_WAY = FILE_WAY;
		try {
			// 获取文件
			getPropCom(url, in);
		} catch (IOException e) {
			throw e;
		} finally {
			// 关闭流
			try {
				in.close();
			} catch (IOException e) {
				throw e;
			} finally {
				in = null;
			}
		}
		return this.prop;
	}
	
	/**
	 * 保存配置文件
	 * 
	 * @param comment
	 * @throws IOException
	 */
	public void saveProp(String comment) throws IOException {
		// 声明输出流
		OutputStream fos = null;
    	try {
    		// 分类初始化输出流
    		if (CLASS_WAY.equals(this.LOAD_WAY)) {
    			// 类方式
    			fos = new FileOutputStream(FileUtils.getClassFileUrl(propUrl));
    		} else if (CLASS_LOADER_WAY.equals(this.LOAD_WAY)) {
    			// 类加载器方式
    			fos = new FileOutputStream(FileUtils.getClassLoaderFileUrl(propUrl));
    		} else if (FILE_WAY.equals(this.LOAD_WAY)) {
    			// 文件流方式
    			fos = new FileOutputStream(this.propUrl);
    		}
    		// 保存配置文件
    		this.prop.store(fos, comment);
    	} catch (IOException e) {
    		throw e;
    	} finally {
    		try {
    			if (null != fos) {
		    		fos.flush();
		            fos.close();
    			}
    		} catch (IOException e) {
    			throw e;
    		} finally {
    			fos = null;
    		}
    		
    	}
	}
	
	/**
	 * 获取文件共通
	 * 
	 * @param url
	 * @param in
	 * @throws IOException
	 */
	private void getPropCom(String url, InputStream in) throws IOException {
    	this.propUrl = url;
    	this.prop = new Properties();
    	this.prop.load(in);
	}
	
	/**
	 * 根据键获取值
	 * 
	 * @param key
	 * @return
	 */
	public String getValueByKey(String key) {
		String result = this.prop.getProperty(key);
		if (null == result) {
			return null;
		} else {
			return result.trim();
		}
	}
	
	/**
	 * 新增或修改键值
	 * 
	 * @param key
	 * @param value
	 */
	public void setValue(String key, String value) {
		this.prop.setProperty(key, value);
	}
	
	/**
	 * 更新键值  由键确定
	 * 
	 * @param key
	 * @param value
	 */
	public void updateValue(String key, String value) {
		this.prop.replace(key, value);
	}
	
	/**
	 * 更新键值  由键值确定
	 * 
	 * @param key
	 * @param value
	 */
	public void updateValue(String key, String oldValue, String newValue) {
		this.prop.replace(key, oldValue, newValue);
	}
	
	/**
	 * 删除键值  由键确定
	 * 
	 * @param key
	 */
	public void removeKey(String key) {
		this.prop.remove(key);
	}
	
	/**
	 * 删除键值  由键值确定
	 * 
	 * @param key
	 */
	public void removeKey(String key, String value) {
		this.prop.remove(key, value);
	}
	
	/**
	 * @return the prop
	 */
	public Properties getProp() {
		return this.prop;
	}

	/**
	 * @return the propUrl
	 */
	public String getPropUrl() {
		return this.propUrl;
	}

	/**
	 * @return the lOAD_WAY
	 */
	public String getLOAD_WAY() {
		return this.LOAD_WAY;
	}
	
}

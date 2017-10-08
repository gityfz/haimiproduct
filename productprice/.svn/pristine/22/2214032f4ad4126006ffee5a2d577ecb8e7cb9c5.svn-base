package com.intelligence.common.log;

import gnu.io.CommPortIdentifier;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

//	public static String md5Uper(String s) throws NoSuchAlgorithmException {
//		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
//				'a', 'b', 'c', 'd', 'e', 'f' };
//		byte[] strTemp = s.getBytes();
//		// 使用MD5创建MessageDigest对象
//		MessageDigest mdTemp = MessageDigest.getInstance("MD5");
//		mdTemp.update(strTemp);
//		byte[] md = mdTemp.digest();
//		int j = md.length;
//		char str[] = new char[j * 2];
//		int k = 0;
//		for (int i = 0; i < j; i++) {
//			byte b = md[i];
//			// System.out.println((int)b);
//			// 将没个数(int)b进行双字节加密
//			str[k++] = hexDigits[b >> 4 & 0xf];
//			str[k++] = hexDigits[b & 0xf];
//		}
//		String md5 = new String(str);
//		return md5.toUpperCase();
//	}
	
	@SuppressWarnings("unchecked")
	public static String toQueryString(Map<?, ?> data) throws UnsupportedEncodingException {
	    StringBuffer queryString = new StringBuffer();

	    for (Entry<?, ?> pair : data.entrySet()) {
	    	if (pair.getValue () instanceof List<?>) {
	    		List<Map<String, Object>> listTmp = (List<Map<String, Object>>)pair.getValue();
	    		for (int i = 0, l = listTmp.size(); i < l; i++) {
	    			for (Map.Entry<String, Object> entry : listTmp.get(i).entrySet()) {
	    				queryString.append(URLEncoder.encode(String.valueOf(pair.getKey()), "UTF-8"));
		    			queryString.append("[");
		    			queryString.append(i);
		    			queryString.append("][");
		    			queryString.append(entry.getKey());
		    			queryString.append("]");
		    			queryString.append("=");
		    			queryString.append(entry.getValue());
		    			queryString.append("&");
	    			}	
	    		}
	    	} else {
	    		queryString.append(URLEncoder.encode (String.valueOf(pair.getKey()), "UTF-8" ) + "=" );
		        queryString.append(URLEncoder.encode (String.valueOf(pair.getValue()), "UTF-8" ) + "&" );	
	    	}
	    }

	    if (queryString.length () > 0) {
	        queryString.deleteCharAt ( queryString.length () - 1 );
	    }

		return queryString.toString ();
	}
	
	/**
     * @Description:获取通信端口类型名称
     * @author:Lu
     * @date:2015-8-29 上午11:35:32
     */
    public static String getPortTypeName(int portType) {
        switch (portType) {
        case CommPortIdentifier.PORT_I2C:
            return "I2C";
        case CommPortIdentifier.PORT_PARALLEL: // 并口
            return "Parallel";
        case CommPortIdentifier.PORT_RAW:
            return "Raw";
        case CommPortIdentifier.PORT_RS485: // RS485端口
            return "RS485";
        case CommPortIdentifier.PORT_SERIAL: // 串口
            return "Serial";
        default:
            return "unknown type";
        }
    }
	
    private static String md5Uper(String s) throws NoSuchAlgorithmException {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		byte[] strTemp = s.getBytes();
		// 使用MD5创建MessageDigest对象
		MessageDigest mdTemp = MessageDigest.getInstance("MD5");
		mdTemp.update(strTemp);
		byte[] md = mdTemp.digest();
		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte b = md[i];
			// System.out.println((int)b);
			// 将没个数(int)b进行双字节加密
			str[k++] = hexDigits[b >> 4 & 0xf];
			str[k++] = hexDigits[b & 0xf];
		}
		String md5 = new String(str);
		return md5;
    }
    
    public static String encodeBase64(byte[]input) throws Exception{  
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod= clazz.getMethod("encode", byte[].class);  
        mainMethod.setAccessible(true);  
         Object retObj=mainMethod.invoke(null, new Object[]{input});  
         return (String)retObj;  
    }  
    
    public static byte[] decodeBase64(String input) throws Exception{  
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod= clazz.getMethod("decode", String.class);  
        mainMethod.setAccessible(true);  
         Object retObj=mainMethod.invoke(null, input);  
         return (byte[])retObj;  
    }  

    
	public static void main(String[] args) throws Exception {
		byte[] b = decodeBase64("f3B1vg9S5o8rEffEmXDDHw==");
		System.out.println(new String(b,"GBK"));
//		String str=md5Uper("{\"tradeId\":\"000\",\"mailNo\":\"000\"}123456");  
//		//不设置字节序时候，默认为大端模式  
//		byte[] b=str.getBytes("UTF-8");//结果==0xFE,0xFF,0x53,0x57,0x4E,0xAC
//		System.out.println(new sun.misc.BASE64Encoder().encode(str.getBytes("GB2312")));
//		CommPortIdentifier.getPortIdentifiers();
		/*
         * 不带参数的getPortIdentifiers方法可以获得一个枚举对象，该对象包含了
         * 系统中每个端口的CommPortIdentifier对象。注意这里的端口不仅仅是指串口，也包括并口。
         * 这个方法还可以带参数，getPortIdentifiers(CommPort)获得已经被应用程序打开的端口
         * 相对应的CommPortIdentifier对象。getPortIdentifier(String portName)
         * 获取指定端口名（比如“COM1”）的CommPortIdentifier对象。
         */
//        java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
//        while (portEnum.hasMoreElements()) {
//            CommPortIdentifier portIdentifier = portEnum.nextElement();
//            System.out.println(portIdentifier.getName() + " - " + getPortTypeName(portIdentifier.getPortType()));
//        }
        
		// lists中的对象String 本身含有compareTo方法，所以可以直接调用sort方法，按自然顺序排序，即升序排序
		// Collections.sort(lists);

		List<Map<String, String>> listTmp = new ArrayList<Map<String, String>>();
//		HashMap<String, String> mapTmp = new HashMap<String, String>();
//		mapTmp.put("skuNum", "2");
//		mapTmp.put("salePrice", "151");
//		mapTmp.put("skuId", "1706");
//		mapTmp.put("province", "江苏省");
//		listTmp.add(mapTmp);
//		Map<String, Object> mapKv = new TreeMap<String, Object>();
//		mapKv.put("uid", "ZKZYWX");
//		mapKv.put("key", "c717642551710e0d8add72c781ddbfc5");
//		mapKv.put("ts", "1477882323272");
//		mapKv.put("serviceName", "orderCreate");
////		mapKv.put("skuId", 1305);
////		mapKv.put("limit", 10);
////		mapKv.put("startTime", "2016-01-01 00:00:00");
////		mapKv.put("endTime", "2016-12-01 00:00:00");
//		mapKv.put("recipientName", "张高圆圆蓓");
//		mapKv.put("recipientCardNo", "522635197608189322");
//		mapKv.put("recipientMobile", "13390921779");
//		mapKv.put("provinceName", "江苏省");
//		mapKv.put("cityName", "南京");
//		mapKv.put("districtName", "秦淮区");
//		mapKv.put("address", "汉中路27号友谊广场20楼");
//		mapKv.put("payPlatform", "011a03");
//		mapKv.put("orderPlatform", "011899");
//		mapKv.put("payOrderNo", "12345615456878954");
//		mapKv.put("paySerialNo", "201603870173037047103774413");
////		mapKv.put("outOrderNo", "1111");
//		mapKv.put("payAmount", 600.0);
//		mapKv.put("payTime", "2016-05-25 14:38:17");
//		mapKv.put("items", listTmp);
//		StringBuffer sb = new StringBuffer();
//		for (Map.Entry<String, Object> entry : mapKv.entrySet()) {
//			sb.append(entry.getKey());
//			sb.append("=");
//			if ("items".equals(entry.getKey())) {
//				sb.append("Array");
//			} else {
//				sb.append(entry.getValue());
//			}
//			sb.append("&");
//		}
//		String sbFin = sb.substring(0, sb.length() - 1);
//		mapKv.put("sign", md5Uper(sbFin));
//		mapKv.remove("key");
//
//		 @SuppressWarnings("deprecation")
//		 DefaultHttpClient client = new DefaultHttpClient();
//		 HttpPost post = new
//		 HttpPost("http://open-test.haimibuy.com/service/orderCreate?"+toQueryString(mapKv));
//		 System.out.println(toQueryString(mapKv));
//		 JSONObject response = null;
//		 try {
////			StringEntity s = new StringEntity(toQueryString(mapKv));
////			s.setContentEncoding("UTF-8");
//		 // s.setContentType("application/json");//发送json数据需要设置contentType
////		  	post.setEntity(s);
//		  	HttpResponse res = client.execute(post);
//		  	System.out.println(res);
//		 if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
//				HttpEntity entity = res.getEntity();
//				String result = EntityUtils.toString(res.getEntity());// 返回json格式：
////				 response = JSONObject.fromObject(result);
//				 System.out.println(result);
//		 }
//		 } catch (Exception e) {
//			 throw new RuntimeException(e);
//		 }

		// System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
		// Method method = obj.getClass().getMethod("set" + att, type);
		// method.invoke(obj, value);
	}


	/**
	 * 获取本机IP
	 * 
	 * @return
	 */
	/*
	 * public static String getLocalIp() { InetAddress inetAddress; String ip =
	 * UNKNOW_IP; try { inetAddress = InetAddress.getLocalHost(); ip =
	 * inetAddress.getHostAddress(); } catch (UnknownHostException e) { } return
	 * ip; }
	 */

	// public static void main(String args[]) throws Exception {

	//

	// System.out.println(Runtime.getRuntime().totalMemory() -
	// Runtime.getRuntime().freeMemory());
	// Method logMsethod = LogFormat.class.getMethod("logCommon", new Class[]
	// {java.lang.Integer.class, java.lang.Object[].class});
	// Method logMsethod = CacheManager.class.getMethod("getCache", new Class[]
	// {java.lang.String.class});
	// System.out.println(Runtime.getRuntime().totalMemory() -
	// Runtime.getRuntime().freeMemory());

	// LogPower logPower = LogPower.getLogger(Main.class);
	// Long pre = System.currentTimeMillis();
	// for (int i = 0; i < 5000; i++) {
	// Object[] objects = {1,2,3,4,5,6,7,8,9};
	// System.out.println(1);
	// System.out.println(objects);
	// System.out.println(1);
	// System.out.println(2);
	// System.out.println(3);
	// System.out.println(4);
	// System.out.println(5);
	// System.out.println(6);
	// System.out.println(7);
	// System.out.println(8);
	// System.out.println(9);
	// for(Object object: objects) {
	// System.out.println(object);
	// }
	// test(1,2,3,4,5,6,7,8,9);
	// LogFormat.logCommon(1, new Object[] {1,2,3,4,5,6,7,8,9});
	// }
	// System.out.println(System.currentTimeMillis() - pre);

	// try {
	// Long pre = System.currentTimeMillis();
	// Object[] objs = {"a", "b", "c", "d", "e", "f", "g"};
	// for (int i = 0; i < 50000; i++) {
	// // 获取java缓存
	// CacheManager cacheManager = CacheManager.getManager();
	// @SuppressWarnings("unchecked")
	// HashMap<String, Object> mapMethod = (HashMap<String,
	// Object>)cacheManager.getCache("logCommon" + "::log");
	//
	// if (null == mapMethod) {
	// Method logMsethod = LogFormat.class.getMethod("logCommon", new Class[]
	// {java.lang.Integer.class, java.lang.Object[].class});
	// System.out.println(logMsethod.toGenericString());
	// mapMethod = new HashMap<String, Object>();
	// mapMethod.put("method", logMsethod);
	// cacheManager.setCache("logCommon" + "::log", mapMethod, 86400*1000l);
	// }
	// ((Method)mapMethod.get("method")).invoke(LogFormat.class, new Object[]
	// {1, objs});
	// }
	//
	//
	// System.out.println(System.currentTimeMillis() - pre);

	// Method logMsethod = LogFormat.class.getMethod("logCommon", new Class[]
	// {java.lang.Integer.class, java.lang.Integer.class});
	// logMsethod.invoke(LogFormat.class, new Object[] {1, 2});

	// } catch (IllegalAccessException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IllegalArgumentException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (NoSuchMethodException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (SecurityException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }

	// Log log=Log.getLogger(Main.class); //write once,use everywhere :)
	// log=Log.getLogger(Main.class);
	// log=Log.getLogger(Main.class);
	/*
	 * LogConfiginitialization lct=new LogConfiginitialization(); lct.init();
	 */
	// ILogInfor mylog=new LogInfor();
	// Log log = LogFactory.getLog(Test.class);
	// log.trace("213132131");
	// test();

	// try {
	// //引发异常
	// int a=2/0;
	// } catch(Exception e) {
	// 控制台打印异常信息
	// e.printStackTrace();
	// 写入到日志文件
	// log.loger.error("此处加入您自定义的错误信息内容", e);
	// log.loger.error("error error error,是不是XML格式?");

	// 以XML方式读配置文件。
	/* DOMConfigurator.configure("xmllog4jconfig_normal.xml"); */
	// log.loger.error("您可以定义自己的日志信息",e);
	// 系统编号不+名字+日志关键字
	/*
	 * log.loger .error("002"+"administrator"+"日志关键字",e);
	 * 
	 * DOMConfigurator.configure("xmllog4jconfig_error.xml");
	 * log.loger.error("002"+"administrator"+"日志关键字"+,e);
	 */

	/*
	 * DOMConfigurator.configure("xmllog4jconfig.xml");
	 * log.loger.error("error error error",e);
	 */

	// 以XML方式读到配置文件。
	/*
	 * DOMConfigurator.configure("xmllog4jconfig_normal.xml");
	 * log.loger.warn("warn ",e);
	 */
	/*
	 * DOMConfigurator.configure("xmllog4jconfig_normal.xml");
	 * log.loger.info("a test ,just a test ");
	 */

	/*
	 * String message="002"+"administrator"+"keyword"+e;
	 * mylog.SetError(message);//"002"+"administrator"+"keyword"+e);
	 */
	// System.out.println(log.getClass().toString());

	// String source,String UserName,String KeyWord,String Content
	/*
	 * //log.setError("002",Main.class.toString(), "adminstrator", "您任意定制的关键字",
	 * e.toString());
	 */
	// log.setError("002", "adminstrator", "您任意定制的关键字", e.toString());

	// 其实就这么一行~~~~~舍不得删注释,是因为那是我的"代码进化"过程:)
	// }

	/*
	 * private static final Logger log = Logger.getLogger(TestLog.class);
	 * 
	 * log.error("出错.");
	 * log.warn("AudioConcat.main(): WARNING: AudioFormats don't match");
	 */

	// }

}
package com.intelligence.common.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.intelligence.common.constant.StringCon;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.log.LogUtils;
import com.intelligence.common.utils.JsonUtils;

@Component
public class HttpClientTools {
	
	/**
	 * 日志类
	 */
	public final static Logger logger = LoggerFactory.getLogger(HttpClientTools.class);
	
	private final static String STRING_FORMAT = "str";
	
	private final static String JSON_FORMAT = "json";
	
	private final static String BASE64_SECURITY = "base64";
	  
	    /** 
	     * HttpClient连接SSL 
	     */  
//	    public void ssl() {  
//	        CloseableHttpClient httpclient = null;  
//	        try {  
//	            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());  
//	            FileInputStream instream = new FileInputStream(new File("d:\\tomcat.keystore"));  
//	            try {  
//	                // 加载keyStore d:\\tomcat.keystore    
//	                trustStore.load(instream, "123456".toCharArray());  
//	            } catch (CertificateException e) {  
//	                e.printStackTrace();  
//	            } finally {  
//	                try {  
//	                    instream.close();  
//	                } catch (Exception ignore) {  
//	                }  
//	            }  
//	            // 相信自己的CA和所有自签名的证书  
//	            @SuppressWarnings("deprecation")
//				SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();  
//	            // 只允许使用TLSv1协议  
//	            @SuppressWarnings("deprecation")
//				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,  
//	                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);  
//	            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();  
//	            // 创建http请求(get方式)  
//	            HttpGet httpget = new HttpGet("https://localhost:8443/myDemo/Ajax/serivceJ.action");  
//	            System.out.println("executing request" + httpget.getRequestLine());  
//	            CloseableHttpResponse response = httpclient.execute(httpget);  
//	            try {  
//	                HttpEntity entity = response.getEntity();  
//	                System.out.println("----------------------------------------");  
//	                System.out.println(response.getStatusLine());  
//	                if (entity != null) {  
//	                    System.out.println("Response content length: " + entity.getContentLength());  
//	                    System.out.println(EntityUtils.toString(entity));  
//	                    EntityUtils.consume(entity);  
//	                }  
//	            } finally {  
//	                response.close();  
//	            }  
//	        } catch (ParseException e) {  
//	            e.printStackTrace();  
//	        } catch (IOException e) {  
//	            e.printStackTrace();  
//	        } catch (KeyManagementException e) {  
//	            e.printStackTrace();  
//	        } catch (NoSuchAlgorithmException e) {  
//	            e.printStackTrace();  
//	        } catch (KeyStoreException e) {  
//	            e.printStackTrace();  
//	        } finally {  
//	            if (httpclient != null) {  
//	                try {  
//	                    httpclient.close();  
//	                } catch (IOException e) {  
//	                    e.printStackTrace();  
//	                }  
//	            }  
//	        }  
//	    }  
	  
	    /** 
	     * post方式提交表单（模拟用户登录请求） 
	     */  
//	    public void postForm() {  
//	        // 创建默认的httpClient实例.    
//	        CloseableHttpClient httpclient = HttpClients.createDefault();  
//	        // 创建httppost    
//	        HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceJ.action");  
//	        // 创建参数队列    
//	        List<namevaluepair> formparams = new ArrayList<namevaluepair>();  
//	        formparams.add(new BasicNameValuePair("username", "admin"));  
//	        formparams.add(new BasicNameValuePair("password", "123456"));  
//	        UrlEncodedFormEntity uefEntity;  
//	        try {  
//	            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
//	            httppost.setEntity(uefEntity);  
//	            System.out.println("executing request " + httppost.getURI());  
//	            CloseableHttpResponse response = httpclient.execute(httppost);  
//	            try {  
//	                HttpEntity entity = response.getEntity();  
//	                if (entity != null) {  
//	                    System.out.println("--------------------------------------");  
//	                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));  
//	                    System.out.println("--------------------------------------");  
//	                }  
//	            } finally {  
//	                response.close();  
//	            }  
//	        } catch (ClientProtocolException e) {  
//	            e.printStackTrace();  
//	        } catch (UnsupportedEncodingException e1) {  
//	            e1.printStackTrace();  
//	        } catch (IOException e) {  
//	            e.printStackTrace();  
//	        } finally {  
//	            // 关闭连接,释放资源    
//	            try {  
//	                httpclient.close();  
//	            } catch (IOException e) {  
//	                e.printStackTrace();  
//	            }  
//	        }  
//	    }  
	  
    /** 
     * 发送 post请求 
     * 
     * @throws PowerException 
     */  
	public static HashMap<String, Object> post(String url, HashMap<String, Object> params, HashMap<String, Object> config) throws PowerException {
		// 初始化日志对象
    	JSONObject logHttp = new JSONObject();
        logHttp.put("params", params);
        
        // 初始化返回结果
        HashMap<String, Object> result = new HashMap<String, Object>();
    	// 初始化http访问对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
        	// 初始化参数
            String urlParam = null;
            // JSON格式参数
            if (null != params && JSON_FORMAT.equals(config.get("paramFormat"))) {
            	Gson gson=new Gson();
            	urlParam = gson.toJson(params);
            }
            // 字符串格式参数
            if (null != params && STRING_FORMAT.equals(config.get("paramFormat"))) {
    			urlParam = toQueryString(params);
            }
            // base64加密
            if (null != urlParam && BASE64_SECURITY.equals(config.get("paramSecurity"))) {
            	urlParam = new String(Base64.getEncoder().encode(urlParam.getBytes(StringCon.UTF_8.getValue())));
            }
            // 创建httpget
        	HttpPost httppost = new HttpPost(url);
//	        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
//	        formparams.add(params);  
//        	UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity((List<? extends NameValuePair>) formparams, "UTF-8");   
//	        httppost.setEntity(uefEntity);
        	if (null != urlParam) {
        		StringEntity strEntity = new StringEntity(urlParam);
    	        strEntity.setContentEncoding(StringCon.UTF_8.getValue());
    	        // 发送json数据需要设置contentType
    	        if (JSON_FORMAT.equals(config.get("paramFormat"))) {
    	        	strEntity.setContentType(StringCon.HTTP_TYPE_JSON.getValue());	        	
    	        }
    	        httppost.setEntity(strEntity);
        	}
            // 执行get请求.    
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {  
                // 获取响应实体    
                HttpEntity entity = response.getEntity();
                // 记录响应状态
                logHttp.put("status", response.getStatusLine());
                String resultOri = EntityUtils.toString(entity);
                if (entity != null) {  
                    // 打印响应内容长度    
                	logHttp.put("length", entity.getContentLength());
                }
                if (!StringCon.STATUS_200.getValue().equals(String.valueOf(response.getStatusLine().getStatusCode()))
                		&& !StringCon.STATUS_201.getValue().equals(String.valueOf(response.getStatusLine().getStatusCode()))){
                	logger.error(LogUtils.interfaceFormat("post方法访问出错", url, StringCon.POST.getValue(), logHttp));
                	return null;
                }
                // base64解密
                if (BASE64_SECURITY.equals(config.get("resultSecurity"))) {
                	resultOri = new String(Base64.getDecoder().decode(resultOri.getBytes(StringCon.UTF_8.getValue())));
                }
                // JSON格式解析
                if (JSON_FORMAT.equals(config.get("resultFormat"))) {
                	result = JsonUtils.getMapFromJson(resultOri);
                } else {
                	result.put("data", resultOri);
                }
            } finally {  
                response.close();  
            }
        } catch (ClientProtocolException e) {  
        	throw new PowerException("http post调用协议错误", e);
        } catch (ParseException e) {  
        	throw new PowerException("http post解析错误", e);  
        } catch (IOException e) {  
        	throw new PowerException("http postIO错误", e);
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
            	throw new PowerException("http postIO错误", e);    
            } 
            logger.info(LogUtils.interfaceFormat("post方法访问日志", url, StringCon.POST.getValue(), logHttp));
        }
        return result;
	}  
	  
	public static void ttttt(int a) {
		a = 2;
	}
	
	public static void main(String[] args) throws PowerException, JsonParseException, JsonMappingException, IOException {
		Enum<ErrorCode> e = ErrorCode.PARAMS_FAIL;
		System.out.println(e.equals(ErrorCode.PARAMS_FAIL));
		
//		List<Integer> testArr = new ArrayList<Integer>(1000);
//		for (int i = 0; i < 1000; i++) {
//			testArr.add(i);
//		}
//		long pre = System.currentTimeMillis();
//		for (int i = 0, l = testArr.size(); i < l; i++) {
//			testArr.get(i);
//		}
//		MemoryCache.initCache();
//		System.out.println(MemoryCache.getObjectSize(testArr));
//		
//		System.out.println(System.currentTimeMillis() - pre);
		
//		ArrayList<HashMap<String, Object>> rateData = new ArrayList<HashMap<String, Object>>();
//		HashMap<String, Object> rateDataTmp = null;
//		for (int i = 0; i < 3; i++) {
//			rateDataTmp = new HashMap<String, Object>();
//			rateDataTmp.put("a", i);
//			rateData.add(rateDataTmp);
//		}
//		System.out.println(rateData);
		
//		ArrayList<Integer> productIds = new ArrayList<Integer>();
//		productIds.add(2);
//		productIds.add(1);
//		productIds.add(5);
//		Collections.sort(productIds, new 	Comparator() {
//		      @Override
//		      public int compare(Object o1, Object o2) {
//		        return ((Integer) o1).compareTo((Integer) o2);
//		      }
//		    });
//		System.out.println(productIds);
		
//		long pre = System.currentTimeMillis();
//		for(int i = 1; i < 10000; i++) {
//			HashMap<String, Integer> range = new HashMap<String, Integer>();
//		}
//		System.out.println(System.currentTimeMillis() - pre);
//		HashMap<String, Object> data = new HashMap<String, Object>();
//		data.put("aaaa", 1);
//		CacheManager.getManager().setCache("aaaa", data, 3000l);
//		CacheManager.getManager().getCache("aaaa");
//		ObjectMapper mapper = new ObjectMapper();
//		for(int i = 1; i < 10000; i++) {
////			HashMap<String, Object> test = new HashMap<String, Object>();
//			JSONObject test = new JSONObject();
////			HashMap<String, Object> test = mapper.readValue("{\"a\":{\"b\":1}}", HashMap.class);
////			JSONObject json = JSONObject.fromObject("{\"a\":{\"b\":1}}");
//		}
//		String json = mapper.writeValueAsString(test);
//        System.out.println(json);
		
//		HashMap<String, Object> retMap = new HashMap<String, Object>();
//		retMap.put("a", 1);
////		JSONObject json = JSONObject.fromObject("{\"a\":{\"b\":1}}");
//		JSONObject json = JSONObject.fromObject("{\"a\":1}");
//		long pre = System.currentTimeMillis();
//		for(int i = 1; i < 1000000; i++) {
//			json.put("b",2);			
//		}
//		System.out.println(System.currentTimeMillis() - pre);
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		System.out.println(new Date());
//		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
//		String str = "\u8ba2\u5355\u4ed8\u6b3e\u5b8c\u6210\uff0c\u6211\u4eec\u5c06\u5c3d\u5feb\u4e3a\u4f60\u5b89\u6392\u53d1\u8d27";
//		System.out.println(unicode2String(str));
//		HashMap<String, Object> params = new HashMap<String, Object>();
////		params.put("userId", 1);
//		HashMap<String, Object> config = new HashMap<String, Object>();
////		config.put("paramFormat", JSON_FORMAT);
////		config.put("paramSecurity", BASE64_SECURITY);
////		config.put("resultFormat", JSON_FORMAT);
////		config.put("resultSecurity", BASE64_SECURITY);
////		System.out.println(get("http://open.haimibuy.com/service/", params, config));
//		System.out.println(post("http://open-test.haimibuy.com/service/getExpressInfo?uid=KLGTEST&ts=1483431414&serviceName=getExpressInfo&orderNo=F00257104798&sign=DC96A42FA4CDA8085701AAD512F05A23", params, config));		
	}
	
	public static String unicode2String(String unicode) {
		 
	    StringBuffer string = new StringBuffer();
	 
	    String[] hex = unicode.split("\\\\u");
	 
	    for (int i = 1; i < hex.length; i++) {
	 
	        // 转换出每一个代码点
	        int data = Integer.parseInt(hex[i], 16);
	 
	        // 追加成string
	        string.append((char) data);
	    }
	 System.out.println(string);
	    return string.toString();
	} 
	
    /** 
     * 发送 get请求 
     * @throws PowerException 
     */  
    public static HashMap<String, Object> get(String url, HashMap<String, Object> params, HashMap<String, Object> config) throws PowerException {
    	// 初始化日志对象
    	JSONObject logHttp = new JSONObject();
        logHttp.put("params", params);
        
        // 初始化返回结果
        HashMap<String, Object> result = new HashMap<String, Object>();
    	// 初始化http访问对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
        	// 初始化参数
            String urlParam = null;
            // JSON格式参数
            if (null != params && JSON_FORMAT.equals(config.get("paramFormat"))) {
            	Gson gson=new Gson();
            	urlParam = gson.toJson(params);
            }
            // 字符串格式参数
            if (null != params && STRING_FORMAT.equals(config.get("paramFormat"))) {
    			urlParam = toQueryString(params);
            }
            // base64加密
            if (null != urlParam && BASE64_SECURITY.equals(config.get("paramSecurity"))) {
            	urlParam = new String(Base64.getEncoder().encode(urlParam.getBytes(StringCon.UTF_8.getValue())));
            }
            // 创建httpget
        	StringBuffer getPa = new StringBuffer();
        	getPa.append(url);
        	getPa.append(StringCon.ASK.getValue());
        	getPa.append(urlParam);
            HttpGet httpget = new HttpGet(getPa.toString());
            // 执行get请求.    
            CloseableHttpResponse response = httpclient.execute(httpget);  
            try {  
                // 获取响应实体    
                HttpEntity entity = response.getEntity();    
                // 记录响应状态
                logHttp.put("status", response.getStatusLine());
                String resultOri = EntityUtils.toString(entity);
                if (entity != null) {  
                    // 打印响应内容长度    
                	logHttp.put("length", entity.getContentLength());
                }
                if (!StringCon.STATUS_200.getValue().equals(String.valueOf(response.getStatusLine().getStatusCode()))) {
                	logger.error(LogUtils.interfaceFormat("get方法访问出错", url, StringCon.GET.getValue(), logHttp));
                	return null;
                }
                // base64解密
                if (BASE64_SECURITY.equals(config.get("resultSecurity"))) {
                	resultOri = new String(Base64.getDecoder().decode(resultOri.getBytes(StringCon.UTF_8.getValue())));
                }
                // JSON格式解析
                if (JSON_FORMAT.equals(config.get("resultFormat"))) {
                	result = JsonUtils.getMapFromJson(resultOri);
                } else {
                	result.put("data", resultOri);
                }
            } finally {  
                response.close();  
            }
        } catch (ClientProtocolException e) {  
            throw new PowerException("http get调用协议错误", e);
        } catch (ParseException e) {  
        	throw new PowerException("http get转换错误", e);  
        } catch (IOException e) {  
        	throw new PowerException("http getIO错误", e);
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
            	throw new PowerException("http getIO错误", e);   
            } 
            logger.info(LogUtils.interfaceFormat("get方法访问日志", url, StringCon.GET.getValue(), logHttp));
        }
        return result;
    }
	  
	    /** 
	     * 上传文件 
	     */  
//	    public void upload() {  
//	        CloseableHttpClient httpclient = HttpClients.createDefault();  
//	        try {  
//	            HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceFile.action");  
//	  
//	            FileBody bin = new FileBody(new File("F:\\image\\sendpix0.jpg"));  
//	            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);  
//	  
//	            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();  
//	  
//	            httppost.setEntity(reqEntity);  
//	  
//	            System.out.println("executing request " + httppost.getRequestLine());  
//	            CloseableHttpResponse response = httpclient.execute(httppost);  
//	            try {  
//	                System.out.println("----------------------------------------");  
//	                System.out.println(response.getStatusLine());  
//	                HttpEntity resEntity = response.getEntity();  
//	                if (resEntity != null) {  
//	                    System.out.println("Response content length: " + resEntity.getContentLength());  
//	                }  
//	                EntityUtils.consume(resEntity);  
//	            } finally {  
//	                response.close();  
//	            }  
//	        } catch (ClientProtocolException e) {  
//	            e.printStackTrace();  
//	        } catch (IOException e) {  
//	            e.printStackTrace();  
//	        } finally {  
//	            try {  
//	                httpclient.close();  
//	            } catch (IOException e) {  
//	                e.printStackTrace();  
//	            }  
//	        }  
//	    } 
    
    /**
     * 将参数转换成字符串
     * 
     * 
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
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
	 * @return the stringFormat
	 */
	public static String getStringFormat() {
		return STRING_FORMAT;
	}


	/**
	 * @return the jsonFormat
	 */
	public static String getJsonFormat() {
		return JSON_FORMAT;
	}


	/**
	 * @return the base64Security
	 */
	public static String getBase64Security() {
		return BASE64_SECURITY;
	}
    
}
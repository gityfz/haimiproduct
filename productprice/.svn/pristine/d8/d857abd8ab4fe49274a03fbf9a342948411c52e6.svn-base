package com.intelligence.common.fileupload;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
 
/**
 * 作者：付学亮
 * 版权：北京数字天域
 * 创建日期： 2011-10-10
 * 此类用途：图片工具类
 */
public final class ImageUtils {  
	
	/**
	 * 四种常规图片格式常量
	 */
	  public static final String PNG="png";  
	  public static final String JPG="jpg";  
	  public static final String BMP="bmp";  
	  public static final String GIF="gif";  
	    
	
	
	/**
	 * 图片保存工具
	 * @param byt 图片二进制字节数组
	 * @param picTo 图片保存路径
	 * @param size 图片尺寸
	 * @param formart 图片格式
	 * @return
	 * 
	 * 
	 */
	
	public static boolean zoom(byte[] byt, String picTo, int size, String formart) {		
		boolean bool = false;
		try {
			RenderedImage   rendImage   =getScaleImage(byt,formart.toUpperCase(), size);
			//以下是将图形保存为标准图片格式
			bool =  ImageIO.write(rendImage,formart.toUpperCase(),new File(picTo));
			
		} catch (IOException e) {
			 
		}finally{
			
		}
		return bool;
	}
	
	/**
	 * 图片名称加密类
	 * @param s   截取的图片名称
	 * @return String 返回加密后的图片名称
	 */
	public final static String MD5(String s) {  
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};         
        try {  
            byte[] btInput = s.getBytes();  
            // 获得MD5摘要算法的 MessageDigest 对象  
            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
            // 使用指定的字节更新摘要  
            mdInst.update(btInput);  
            // 获得密文  
            byte[] md = mdInst.digest();  
            // 把密文转换成十六进制的字符串形式  
            int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {  
                byte byte0 = md[i];  
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
                str[k++] = hexDigits[byte0 & 0xf];  
            }  
            return new String(str);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
	
    /**
     * 读取指定路径图片文件
     * @param path
     * @return
     * @throws IOException
     */
    public static byte[] readFromFile(String path) throws IOException {  
        InputStream is = new FileInputStream(new File(path));  
        byte[] buf = new byte[is.available()];  
        is.read(buf);  
        is.close();  
        return buf;  
    }  
    
    
//    public static byte[] readFromFile(File file) throws IOException {  
//        InputStream is = new FileInputStream(file);  
//        byte[] buf = new byte[is.available()];  
//        is.read(buf);  
//        is.close();  
//        return buf;  
//    } 
  
    /** 
     * 构建一个image对象实例
     *  
     * @param img 图片二进制字节数组
     * @return ImageInfo对象实例
     * @throws IOException 
     */  
    public static ImageInfo getImageInfo(byte[] img) throws IOException {  
        ByteArrayInputStream bais = new ByteArrayInputStream(img);  
        MemoryCacheImageInputStream is=new MemoryCacheImageInputStream(bais);  
        Iterator<ImageReader> it=ImageIO.getImageReaders(is);  
        ImageReader r=null;  
        while(it.hasNext()){  
            r=it.next();  
            break;  
        }  
        if(r==null){  
            return null;  
        }  
        ImageInfo i=new ImageInfo();  
        i.setType(r.getFormatName().toLowerCase());  
        int index=r.getMinIndex();  
        /** 
         * 对于ImageReader的线程安全是不确定的 
         */  
        synchronized (r) {  
            r.setInput(is);  
            i.setHeight(r.getHeight(index));  
            i.setWidth(r.getWidth(index));  
        }  
        return i;  
    }  
    public static BufferedImage getImage(byte[] img) throws IOException {  
        ByteArrayInputStream bais = new ByteArrayInputStream(img);  
        BufferedImage src = ImageIO.read(bais);  
        return src;  
    }  
    /** 
     * 等比例缩放 
     * @param img 图片二进制字节数组
     * @param width 图片尺寸(宽度)
     * @return BufferedImage 生成图片
     * @throws IOException 
     * 
     */  
    public static BufferedImage getScaleImage(byte[] img,String type,int width) throws IOException {  
        ByteArrayInputStream bais = new ByteArrayInputStream(img);  
        BufferedImage src = ImageIO.read(bais);  
        int w=src.getWidth();  
        int h=src.getHeight();  
        int height=(int) (((float)width/w)*h);  
        Image im=src.getScaledInstance(width, height,Image.SCALE_SMOOTH);  
        BufferedImage bi=new BufferedImage(width, height, src.getType());  
        bi.getGraphics().drawImage(im, 0, 0,null);  
        return bi;  
    }  
    
    /** 
     * 等比例缩放 
     * @param img 图片二进制字节数组
     * @param width 图片尺寸(宽度)
     * @return 图片二进制字节数组
     * @throws IOException 
     * 
     */  
    public static byte[] getScaleImageBytes(byte[] img,String type,int width) throws IOException {  
        BufferedImage bi=getScaleImage(img, type, width);  
        ByteArrayOutputStream out=new ByteArrayOutputStream();  
        ImageIO.write(bi, type, out);  
        return out.toByteArray();  
    }  
    
    
    /** 
     * 按照指定长宽缩放 
     * @param img 图片二进制字节数组
     * @param width 图片尺寸(宽度)
     * @return 图片二进制字节数组
     * @throws IOException 
     * 
     */ 
    public static byte[] getScaleImageBytes(byte[] img,String type,int width,int height) throws IOException {  

    	 ByteArrayInputStream bais = new ByteArrayInputStream(img);  
         BufferedImage src = ImageIO.read(bais);  
         Image im=src.getScaledInstance(width, height,Image.SCALE_SMOOTH);  
         BufferedImage bi=new BufferedImage(width, height, src.getType());  
         bi.getGraphics().drawImage(im, 0, 0,null);  
    	ByteArrayOutputStream out=new ByteArrayOutputStream();  
        ImageIO.write(bi, type, out);  
        return out.toByteArray();  
    }  
    
    
    /** 
     * 获取文件类型,没找到返回null,这方法太高效了,可能不准确, 
     * 这个是我看的网上的，有bug不准确 
     * @param byte1 
     * @return 
     */  
    public static String fastParseFileType(byte[] byte1) {  
        if ((byte1[0] == 71) && (byte1[1] == 73) && (byte1[2] == 70)  
                && (byte1[3] == 56) && ((byte1[4] == 55) || (byte1[4] == 57))  
                && (byte1[5] == 97)) {  
            return GIF;  
        }  
        if ((byte1[6] == 74) && (byte1[7] == 70) && (byte1[8] == 73)  
                && (byte1[9] == 70)) {  
            return JPG;  
        }  
        if ((byte1[0] == 66) && (byte1[1] == 77)) {  
            return BMP;  
        }  
        if ((byte1[1] == 80) && (byte1[2] == 78) && (byte1[3] == 71)) {  
            return PNG;  
        }  
        return null;  
    }
    
    
    /**
     * 图片实例内部类
     * @author Administrator
     *
     */
    public static class ImageInfo{  
        private String type;  
        private int width;  
        private int height;  
        public String getType() {  
            return type;  
        }  
        public void setType(String type) {  
            this.type = type;  
        }  
        public int getWidth() {  
            return width;  
        }  
        public void setWidth(int width) {  
            this.width = width;  
        }  
        public int getHeight() {  
            return height;  
        }  
        public void setHeight(int height) {  
            this.height = height;  
        }  
    }  
}  
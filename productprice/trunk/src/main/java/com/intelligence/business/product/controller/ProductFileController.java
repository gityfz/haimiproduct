package com.intelligence.business.product.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.intelligence.business.product.service.IProductFileService;
import com.intelligence.common.base.BaseController;
import com.intelligence.common.fileupload.ImageUtils;
import com.intelligence.common.fileupload.ImageUtils.ImageInfo;
import com.intelligence.common.log.LogUtils;

@Component
@Controller
@RequestMapping(value = "/product-file")
public class ProductFileController extends BaseController{
	
	public final static Logger logger = LoggerFactory.getLogger(ProductFileController.class);
		
	@Resource
	private IProductFileService iProductFileService;
	
	@Resource
	private HttpServletRequest httpRequest;
	
	@Resource
	private HttpServletResponse httpResponse;

	
	/**
	 * @author Zhengyf
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * 测试图片上传方法
	 */
	@RequestMapping(value = "/testUploadImage", method = RequestMethod.POST)
	public ModelAndView testUploadImage(@RequestParam(value = "file", required = false)  MultipartFile file,MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");  //设置编码
		String size = request.getParameter("size");
		/**
		 * 默认图片宽度768
		 */
		int sizeInt= 768;
		
		/**
		 * 限制图片最大宽度2000
		 */
		if(size!=null){
		if(!(Integer.parseInt(size)>0)  || (Integer.parseInt(size)>2000)){
			sizeInt=768;
		}else{
			sizeInt= Integer.parseInt(size);
		}
		}
		if(! (0<file.getSize())){
			return new ModelAndView("/index");
//			return "success";
		}
		
		ModelAndView view = new ModelAndView("/success");  
		
		/**
		 * 获取前台提交file文件
		 */
		CommonsMultipartFile cf= (CommonsMultipartFile)file; 
		 DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
		 File f = fi.getStoreLocation();
		 
		 /**
		  * 字节数组中读入文件流
		  */
		 InputStream is = new FileInputStream(f);  
         byte[] buf = new byte[is.available()];  
         is.read(buf);  
         is.close(); 
		 
         /**
          * 获取图片长宽
          */
         ImageInfo imageInfo =ImageUtils.getImageInfo(buf);
         int width = imageInfo.getWidth();
         int height= imageInfo.getHeight();
         String img_width= Integer.toString(sizeInt);
         String img_height= Integer.toString((int)(((float)sizeInt/width)*height));
         
         /**
          * 计算文件路径，文件名，类型
          */
		 String path = request.getSession().getServletContext().getRealPath("/upload");
		 String fileName = new Date().getTime()+"";
		 String type = ImageUtils.fastParseFileType(buf)==null?ImageUtils.JPG:ImageUtils.fastParseFileType(buf);
		 
		 fileName=ImageUtils.MD5(fileName);  //加密文件名
         String realFileName = fileName+"."+type;
         if(logger.isInfoEnabled()){
        	 logger.info(LogUtils.commonFormat("测试图片上传", "文件名:",realFileName,"路径:",path,"图片格式:",type));
         }
//         System.out.println("测试图片上传"+path+"文件名为："+realFileName+"图片格式为:"+type);
         
         
         /**
          * 检查服务器保存路径是否存在，弱不存在，新建目标文件夹
          */
         File targetFile = new File(path);  
         if(!targetFile.exists()){  
             targetFile.mkdirs();  
         }  
         
         /**
          * 按照指定格式尺寸保存图片文件
          */
         try {  
        	 ImageUtils.zoom(buf, path+"\\"+realFileName, sizeInt,type);
         } catch (Exception e) {  
             e.printStackTrace();  
         }  
         
         
         /**
          * 存储图片信息到数据库
          */
         
        int id =iProductFileService.getMaxId();
        String img_type=null;
        switch(type){
        case ImageUtils.JPG :img_type="010w01";break;
        case ImageUtils.PNG :img_type="010w02";break;
        case ImageUtils.BMP :img_type="010w03";break;
        case ImageUtils.GIF :img_type="010w04";break;
        default :img_type="010w01";
       
        }
        iProductFileService.fileUpload(id, fileName, "/upload/"+fileName,img_width,img_height,img_type);
		return view;
//    	return "success";
        
    }

	
	/**
	 * 按指定尺寸切图显示图片流
	 * @param id 图片id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/readImage", method = RequestMethod.GET)
	public void readImage(@RequestParam(value = "id")  int id
//			,@RequestParam(value = "size", required = false) int size
			,HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");  //设置编码
//		ModelAndView view = new ModelAndView("/image");
//		return view;
		
		/**
		 * 检查是否传入图片宽度，高度参数
		 */
		String sizeString = request.getParameter("size");
		String heightString = request.getParameter("height");
		int size=sizeString!=null?Integer.parseInt(sizeString):0;
		int height=heightString!=null?Integer.parseInt(heightString):0;
//		String height = request.getParameter("height");
		
		Map<String, Object> fileCase= iProductFileService.getFileCase(id);
		String fileName=null;
		String fileType=null;
		int img_width=0;
		int img_height=0;
		
		
		if(null==fileCase){
//			return null;
		}else{
			fileName=fileCase.get("name").toString();
			img_width=Integer.parseInt(fileCase.get("weight").toString());	
			img_height=Integer.parseInt(fileCase.get("height").toString());	
			fileType=fileCase.get("type").toString();			
		switch(fileType){
					case "010w01": fileType=ImageUtils.JPG;break;
					case "010w02": fileType=ImageUtils.PNG;break;
					case "010w03": fileType=ImageUtils.BMP;break;
					case "010w04": fileType=ImageUtils.GIF;break;
					default: fileType=ImageUtils.JPG;
					break;
				
				
				}
		}
		
		fileName=fileName+"."+fileType;
		
		String path = request.getSession().getServletContext().getRealPath("/")+"upload"+"\\"+fileName;
		byte[] img = ImageUtils.readFromFile(path);
		String type = ImageUtils.fastParseFileType(img);
		
		/**
		 * 根据传入参数进行缩放切图或者显示原图
		 */
		if(size>0 && height >0){
			if (logger.isInfoEnabled()) {
				logger.info(LogUtils.commonFormat("不按比例缩放，宽为：",size,"高为：",height));
			}
//			System.out.println("不按比例缩放，宽为："+size+"高为："+height);
			img = ImageUtils.getScaleImageBytes(img, type, size,height);
		}else{
			if(height<=0 && size<=0){
				size=img_width;
				height=img_height;
				if (logger.isInfoEnabled()) {
					logger.info(LogUtils.commonFormat("无传入参数，读取原图","宽为",size,"高为",height));
				}
			}else if(height >0){
				size=(int)(((float)img_width*height)/img_height);
				if (logger.isInfoEnabled()) {
					logger.info(LogUtils.commonFormat("以高度按比例缩放","高为：",height));
				}
			}else{
				if (logger.isInfoEnabled()) {
					logger.info(LogUtils.commonFormat("以宽度按比例缩放","宽为：",size));
				}
			}
			img = ImageUtils.getScaleImageBytes(img, type, size);
		}
		
		
//		img = ImageUtils.getScaleImageBytes(img, type, size);
		InputStream in = new ByteArrayInputStream(img); 
		BufferedOutputStream imgOut = new BufferedOutputStream(response.getOutputStream());  
		
		/**
		 * 输出图片流
		 */
		try {  
	        byte[] b=new byte[1024];
			int len = in.read(b);  
	        while (len > 0) {  
	            imgOut.write(b, 0, len);  
	            len = in.read(b);  
	        }
	        
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    } finally {  
	    	imgOut.flush();
	        imgOut.close();
	        in.close(); 
	    }  
//		return view;
	}
}

package com.intelligence.business.product.service.impl;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.intelligence.business.product.dao.IProductFileDao;
import com.intelligence.business.product.service.IProductFileService;

@Service
public class ProductFileServiceImpl implements IProductFileService{

	@Resource
	private IProductFileDao iProductFileDao;
	
	/** @param
	 *  @return int
	 *  获取当前最大id
	 * 
	 */
	public int getMaxId() {
		if(iProductFileDao.getMaxId()==null){
			return 0;
		}else{
		return iProductFileDao.getMaxId()+1;
		}
	}
	
	/**
	 * 图片文件上传记录
	 * @param id 文件id
	 * @param name 文件名
	 * @param path 文件服务器路径
	 * @param w 图片长度
	 * @param h 图片高度
	 * @param t 图片类型
	 */
	public void fileUpload(int id,String name ,String path,String w,String h ,String t) {
		iProductFileDao.fileUpload(id, name, path,w,h,t);
	}


	/**
	 * 获取文件名
	 */
	public String getFileName(int id) {
		return iProductFileDao.getFileName(id);
	}

	/**
	 * 获取文件路径
	 */
	public String getFilePath(int id) {
		return iProductFileDao.getPathName(id);
	}


	/**
	 * 根据id获取文件实例 
	 */
	public Map<String, Object> getFileCase(int id) {
		return iProductFileDao.getFileCase(id);
	}

}

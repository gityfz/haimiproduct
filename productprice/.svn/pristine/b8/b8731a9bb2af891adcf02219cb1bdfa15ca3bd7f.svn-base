package com.intelligence.business.product.dao.impl;

import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.intelligence.business.product.dao.IProductFileDao;
import com.intelligence.business.product.persistance.ProductFileMapper;

@Repository
public class ProductFileImpl implements IProductFileDao{

	@Resource
	private ProductFileMapper productFileMapper;
	int maxId =0;
	public void fileUpload() {
		if(!(0<=productFileMapper.getMaxId())){
			maxId = productFileMapper.getMaxId();
		    maxId++;
		}else{
			maxId = 0;
		}
		
		
	}
	@Override
	public void fileUpload(int id, String name, String path,String w,String h ,String t) {
		productFileMapper.insertUpload(id, name, path,w,h,t);
	}
	
	public Integer getMaxId() {
		return productFileMapper.getMaxId();
	}
	
	public String getFileName(int id) {
		String name=null;
		Map<String,Object> map = productFileMapper.getUploadMap(id);
		for(Entry<String, Object> entry:map.entrySet()){
			if("name".equals(entry.getKey())){
				name = entry.getValue().toString();
			}
		}
		if(name!=null){
			return name;
		}else
			return null;
	}

	public String getPathName(int id) {
		String path=null;
		Map<String,Object> map = productFileMapper.getUploadMap(id);
		for(Entry<String, Object> entry:map.entrySet()){
			if("path".equals(entry.getKey())){
				path = entry.getValue().toString();
			}
		}
		if(path!=null){
			return path;
		}else
			return null;
	}
	
	public Map<String, Object> getFileCase(int id) {
		
		return productFileMapper.getUploadMap(id);
	}
	
}

package com.intelligence.business.product.dao;

import java.util.Map;

public interface IProductFileDao {

	
	public Integer getMaxId();

	public String getFileName(int id);

	public String getPathName(int id);

	public void fileUpload(int id, String name, String path, String w,
			String h, String t);

	public Map<String, Object> getFileCase(int id);
}

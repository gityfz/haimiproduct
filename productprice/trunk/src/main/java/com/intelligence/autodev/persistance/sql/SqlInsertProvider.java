/**
 * 
 */
package com.intelligence.autodev.persistance.sql;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import com.intelligence.common.constant.DateCon;
import com.intelligence.common.utils.DateUtils;

/**
 * @author p-sunhao
 *
 */
public class SqlInsertProvider {
	
	@SuppressWarnings("unchecked")
	public String getInsertUpdMySql(Map<String, Object> parameters) {
		// 初始化返回的插入SQL
		StringBuilder insertSql = new StringBuilder();
				
		try {
		
			insertSql.append(SqlEle.INSERT.getStrValue());
			insertSql.append(SqlEle.INTO.getStrValue());
			// 拼接表名
			insertSql.append(parameters.get("tablename").toString());
			// 拼接列名
			HashMap<String, Object> mapCol = getInsertColumnSql((HashSet<String>)parameters.get("insertcolumn"), (HashSet<String>)parameters.get("defaultcolumn"));
			insertSql.append(mapCol.get("headSql"));
			// 拼接数据
			insertSql.append(SqlEle.RIGHT_BRACKET.getStrValue());
			insertSql.append(SqlEle.VALUES.getStrValue());
			insertSql.append(getInsertDataSql((List<HashMap<String, Object>>) parameters.get("insertdata"), 
			(HashMap<String, Object>)parameters.get("defaultdata"), (HashMap<String, Object>)parameters.get("addAttribute"), (LinkedHashSet<String>)mapCol.get("colSeq")));
			// 拼接更新关键词
			insertSql.append(SqlEle.ON.getStrValue());
			insertSql.append(SqlEle.DUPLICATE.getStrValue());
			insertSql.append(SqlEle.KEY.getStrValue());
			insertSql.append(SqlEle.UPDATE.getStrValue());
			// 拼接更新列
			insertSql.append(getUpdateColumnSql((HashSet<String>)parameters.get("insertcolumn"), (HashSet<String>)parameters.get("defaultcolumn"), parameters.get("primaryKey").toString()));
			// 拼接分号
			insertSql.append(SqlEle.SEMICOLON.getStrValue());
		} catch(Exception e) {
		}
				
		// 返回SQL
		return insertSql.toString();
	}
	
	/**
	 * 获取插入语句
	 * 
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getInsertSql(Map<String, Object> parameters) {
		// 初始化返回的插入SQL
		StringBuilder insertSql = new StringBuilder();
		
		try {
			
			insertSql.append(SqlEle.INSERT.getStrValue());
			insertSql.append(SqlEle.INTO.getStrValue());
			// 拼接表名
			insertSql.append(parameters.get("tablename").toString());
			// 拼接列名
			HashMap<String, Object> mapCol = getInsertColumnSql((HashSet<String>)parameters.get("insertcolumn"), (HashSet<String>)parameters.get("defaultcolumn"));
			insertSql.append(mapCol.get("headSql"));
			// 拼接数据
			insertSql.append(SqlEle.RIGHT_BRACKET.getStrValue());
			insertSql.append(SqlEle.VALUES.getStrValue());
			insertSql.append(getInsertDataSql((List<HashMap<String, Object>>) parameters.get("insertdata"), 
					(HashMap<String, Object>)parameters.get("defaultdata"), (HashMap<String, Object>)parameters.get("addAttribute"), (LinkedHashSet<String>)mapCol.get("colSeq")));
			// 拼接分号
			insertSql.append(SqlEle.SEMICOLON.getStrValue());
		} catch(Exception e) {
		}
		
		// 返回SQL
		return insertSql.toString();
	}
	
	/**
	 * 生成插入SQL列名函数
	 * 
	 * @param columns
	 * @return
	 */
	public HashMap<String, Object> getInsertColumnSql(HashSet<String> columns, HashSet<String> defaultColumns) {
		// 初始化返回结果
		HashMap<String, Object> mapRe = new HashMap<String, Object>();
		// 初始化插入列序列
		LinkedHashSet<String> colSeq = new LinkedHashSet<String>();
		
		// 初始化SQL对象
		StringBuilder insertHeadSql = new StringBuilder();
		
		// 初始化插入SQL头部
		insertHeadSql.append(SqlEle.LEFT_BRACKET.getStrValue());
		
		// 循环插入列名
		if (null != columns && 0 != columns.size()) {
			for (String column:columns) {
				colSeq.add(column);
				insertHeadSql.append(column);
				insertHeadSql.append(SqlEle.COMMA.getStrValue());
			}
		}
		
		// 插入默认列名
		if (null != defaultColumns && 0 != defaultColumns.size()) {
			for (String defaultColumn:defaultColumns) {
				colSeq.add(defaultColumn);
				insertHeadSql.append(defaultColumn);
				insertHeadSql.append(SqlEle.COMMA.getStrValue());
			}
		}
		
		// 整合返回结果
		mapRe.put("headSql", insertHeadSql.substring(0, insertHeadSql.length() - 1));
		mapRe.put("colSeq", colSeq);
		
		// 置空变量
		insertHeadSql = null;
		colSeq = null;
		
		// 返回结果
		return mapRe;
	}
	
	/**
	 * 生成更新SQL列名函数
	 * 
	 * @param columns
	 * @return
	 */
	public String getUpdateColumnSql(HashSet<String> columns, HashSet<String> defaultColumns, String primaryKey) {
		
		// 初始化SQL对象
		StringBuilder updateSql = new StringBuilder();
		
		//		product_name= values  ( product_name ) ,update_uid= values  ( update_uid ) ;
		// 循环插入列名
		if (null != columns && 0 != columns.size()) {
			for (String column:columns) {
				if (!column.equals(primaryKey)) {
					updateSql.append(column);
					updateSql.append(SqlEle.EQUALITY.getStrValue());
					updateSql.append(SqlEle.VALUES.getStrValue());
					updateSql.append(SqlEle.LEFT_BRACKET.getStrValue());
					updateSql.append(column);
					updateSql.append(SqlEle.RIGHT_BRACKET.getStrValue());
					updateSql.append(SqlEle.COMMA.getStrValue());
				}
			}
		}
		
		// 插入默认列名
		if (null != defaultColumns && 0 != defaultColumns.size()) {
			for (String defaultColumn:defaultColumns) {
				if (!defaultColumn.equals(primaryKey)) {
					updateSql.append(defaultColumn);
					updateSql.append(SqlEle.EQUALITY.getStrValue());
					updateSql.append(SqlEle.VALUES.getStrValue());
					updateSql.append(SqlEle.LEFT_BRACKET.getStrValue());
					updateSql.append(defaultColumn);
					updateSql.append(SqlEle.RIGHT_BRACKET.getStrValue());
					updateSql.append(SqlEle.COMMA.getStrValue());
				}
			}
		}
		
		// 返回结果
		return updateSql.substring(0, updateSql.length() - 1);
	}
	
	/**
	 * 生成插入SQL数据函数
	 * 
	 * @param columns
	 * @return
	 */
	public String getInsertDataSql(List<HashMap<String, Object>> data, HashMap<String, Object> defaultData, 
									HashMap<String, Object> addAttribute, LinkedHashSet<String> colSeq) {
		
		// 初始化SQL对象
		StringBuilder insertDataSql = new StringBuilder();
		
		// 处理动态数据集合
		if (null != colSeq && 0 != colSeq.size()
			 && null != data && 0 != data.size()) {
			
			// 初始化数据类型映射关系
			HashMap<String, String> mapType = new HashMap<String, String>();
			// 插入第一行数据
			// 拼接左括号
			StringBuilder insertSingleDataSqlOne = new StringBuilder(SqlEle.LEFT_BRACKET.getStrValue());
			// 初始化列序列
			Iterator<String> itCol = colSeq.iterator();
			// 取第一行数据
			HashMap<String, Object> dataObjOne = data.get(0);
			while(itCol.hasNext()) {
				// 引用列名
				String colName = itCol.next();
				// 引用列值
				Object temp = (null != defaultData && null != defaultData.get(colName) ? defaultData.get(colName) : dataObjOne.get(colName));
				// 引用列类型名
				String colType = temp.getClass().getName();
				// 插入列类型名与类型映射
				mapType.put(colName, colType);
				// 拼接列值
				insertSingleDataSqlOne.append(SqlEle.COMMA_UP.getStrValue());
				insertSingleDataSqlOne.append(getColValue(colType, temp, addAttribute));
				insertSingleDataSqlOne.append(SqlEle.COMMA_UP.getStrValue());
				// 拼接逗号
				insertSingleDataSqlOne.append(SqlEle.COMMA.getStrValue());
				// 释放临时变量
				colName = null;
				temp = null;
				colType = null;
			}
			
			// 插入总SQL
			insertDataSql.append(insertSingleDataSqlOne.substring(0, insertSingleDataSqlOne.length() - 1));
			insertDataSql.append(SqlEle.RIGHT_BRACKET.getStrValue());
			insertSingleDataSqlOne = null;
			itCol = null;
			
			// 循环插入后续的数据
			for (int i = 1, m = data.size(); i < m; i++) {
				// 拼接逗号
				insertDataSql.append(SqlEle.COMMA.getStrValue());
				// 拼接左括号
				StringBuilder insertSingleDataSql = new StringBuilder(SqlEle.LEFT_BRACKET.getStrValue());
				// 初始化列序列
				Iterator<String> itColData = colSeq.iterator();
				// 取第一行数据
				HashMap<String, Object> dataObj = data.get(i);
				while(itColData.hasNext()) {
					// 引用列名
					String colName = itColData.next();
					// 引用列值
					Object temp = (null != defaultData && null != defaultData.get(colName) ? defaultData.get(colName) : dataObj.get(colName));
					// 拼接列值
					insertSingleDataSql.append(SqlEle.COMMA_UP.getStrValue());
					insertSingleDataSql.append(getColValue(mapType.get(colName), temp, addAttribute));
					insertSingleDataSql.append(SqlEle.COMMA_UP.getStrValue());
					// 拼接逗号
					insertSingleDataSql.append(SqlEle.COMMA.getStrValue());
					// 释放临时变量
					colName = null;
					temp = null;
				}
				
				// 插入总SQL
				insertDataSql.append(insertSingleDataSql.substring(0, insertSingleDataSql.length() - 1));
				insertDataSql.append(SqlEle.RIGHT_BRACKET.getStrValue());
				insertSingleDataSql = null;
			}
				
			
		}
		
		// 返回结果
		return insertDataSql.toString();
	}

	/**
	 * 获取可以插入数据库的数据
	 * 
	 * @param colType
	 * @param colValue
	 * @return
	 */
	public String getColValue(String colType, Object colValue, HashMap<String, Object> addAttribute) {
		// 分类转换数据类型
		if (SqlEle.STRING_CLASS.getStrValue().equals(colType)
				|| SqlEle.INTEGER_CLASS.getStrValue().equals(colType)
				|| SqlEle.DOUBLE_CLASS.getStrValue().equals(colType)) {
			return colValue.toString();
		} else if (SqlEle.DATE_CLASS.getStrValue().equals(colType) 
				&& null != addAttribute 
				&& null != addAttribute.get(colType) 
				&& null != addAttribute.get(addAttribute.get(colType).toString())) {
			return DateUtils.dateToString((Date) colValue, 
					addAttribute.get(addAttribute.get(colType).toString()).toString());
		} else if (SqlEle.DATE_CLASS.getStrValue().equals(colType)) {
			return DateUtils.dateToString((Date) colValue, DateCon.yyyy_MM_dd_HH_mm_ss.getValue());
		} else {
			return colValue.toString();
		}
	}
    
}

/**
 * 
 */
package com.intelligence.autodev.persistance.sql;

/**
 * @author p-sunhao
 *
 */
public enum SqlEle {
	
	/*** 关键词start ***/
	SELECT(" SELECT "),
	FROM(" FROM "),
	WHERE(" WHERE "),
	AND(" AND "),
	DELETE(" DELETE "),
	OR(" OR "),
	LEFT(" LEFT "),
	RIGHT(" RIGHT "),
	INNOR(" INNOR "),
	JOIN(" JOIN "),
	IN(" IN "),
	ON(" ON "),
	NOT(" NOT "),
	CASE(" CASE "),
	WHEN(" WHEN "),
	IF(" IF "),
	ELSE(" ELSE "),
	END(" END "),
	ORDER(" ORDER "),
	GROUP(" GROUP "),
	ASC(" ASC "),
	DESC(" DESC "),
	BY(" BY "),
	EXISTS(" EXISTS "),
	LIMIT(" LIMIT "),
	OFFSET(" OFFSET "),
	INSERT(" INSERT "),
	INTO(" INTO "),
	UPDATE(" UPDATE "),
	SET(" SET "),
	VALUES(" VALUES "),
	DUPLICATE(" DUPLICATE "),
	TABLE(" TABLE "),
	DATEBASE(" DATEBASE "),
	CREATE(" CREATE "),
	COLUMN(" COLUMN "),
	KEY(" KEY "),
	INDEX(" INDEX "),
	ADD(" ADD "),
	DROP(" DROP "),
	MODIFY(" MODIFY "),
	NULL(" NULL "),
	IS(" IS "),
	AS(" AS "),
	PRIMARY(" PRIMARY "),
	AUTO_INCREMENT(" AUTO_INCREMENT "),
	UNIQUE(" UNIQUE "),
	/*** 关键词end ***/
	
	/*** 符号start ***/
	LEFT_BRACKET(" ( "),
	RIGHT_BRACKET(" ) "),
	COMMA(","),
	COMMA_UP("'"),
	SEMICOLON(";"),
	EMPTY_STR(" "),
	LT(" < "),
	GT(" > "),
	EQUALITY_LT(" <= "),
	EQUALITY_GT(" >= "),
	EQUALITY(" = "),
	SPOT("."),
	/*** 符号end ***/
	
	/*** 数量限制start ***/
	DATA_LIMIT(1000),
	/*** 数量限制end ***/
	
	/*** 数据类型start ***/
	STRING_CLASS("java.lang.String"),
	INTEGER_CLASS("java.lang.Integer"),
	DOUBLE_CLASS("java.lang.Double"),
	DATE_CLASS("java.util.Date"),
	/*** 数据类型end ***/
	;
	
	
	// 字符值
	private String valueStr = null;
	
	// 数量限制
	private Integer valueInt = null;

    /**
     * 字符串枚举类构造函数
     * 
     * @param id
     * @param message
     */
    SqlEle(String val) {
    	this.valueStr = val;
    }
    
    /**
     * 整形枚举类构造函数
     * 
     * @param id
     * @param message
     */
    SqlEle(Integer val) {
    	this.valueInt = val;
    }

    /**
     * @return the value
     */
    public String getStrValue() {
        return this.valueStr;
    }
    
    /**
     * @return the value
     */
    public Integer getIntValue() {
        return this.valueInt;
    }
    
}

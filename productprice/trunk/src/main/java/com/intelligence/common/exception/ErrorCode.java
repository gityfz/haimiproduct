/**
 * 
 */
package com.intelligence.common.exception;

/**
 * @author p-sunhao
 *
 */
public enum ErrorCode {
	
	// 通用成功
	COMMON_SUCCESS(600000, "成功"),
	// 通用失败 
	COMMON_FAIL(610000, "失败"),
	// 查询成功
	QUERY_SUCCESS(600003, "查询成功"),
	// 新增成功
	ADD_SUCCESS(600004, "新增成功"),
	// 更新成功
	UPDATE_SUCCESS(600005, "更新成功"),
	// 保存成功
	SAVE_SUCCESS(600006, "保存成功"),
	// 删除成功
	DELETE_SUCCESS(600007, "删除成功"),
	// 审核成功
	CHECK_SUCCESS(600008, "审核成功"),
	// 操作成功
	OPERATE_SUCCESS(600009, "操作成功"),
	// 查询失败
	QUERY_FAIL(600010, "查询失败"),
	// 新增失败
	ADD_FAIL(600011, "新增失败"),
	// 更新失败
	UPDATE_FAIL(600012, "更新失败"),
	// 保存失败
	SAVE_FAIL(600013, "保存失败"),
	// 删除失败
	DELETE_FAIL(600014, "删除失败"),
	// 审核失败
	CHECK_FAIL(600015, "审核失败"),
	// 操作失败
	OPERATE_FAIL(600016, "操作失败"),
	// 参数错误
	PARAMS_FAIL(610017, "参数错误"),
	// 系统异常 
	SYSTEM_FAIL(610018, "系统异常"),
	// 返回数据格式错误 
	RETURN_FORMAT_FAIL(610019, "返回数据格式错误 "),
	// 类型转换异常 
	CAST_FAIL(610020, "系统异常"),
	// 已经删除，请勿重复删除 
	DOU_DEL_FAIL(610021, "已经删除，请勿重复删除"),
	// 商品正在下单流程中，无法删除 
	ORDER_PRO_DEL_FAIL(610022, "商品正在下单流程中，无法删除"),
	// 获取不到商品异常 
	NO_PRODUCT_FAIL(610023, "获取不到商品异常 ");
	
	/**
	 * 异常编码
	 */
    private Integer errorCode;
    
    /**
     * 异常信息
     */
    private String errorMessage;

    /**
     * 
     * @param id
     * @param message
     */
	private ErrorCode(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * @return the id
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * @return the message
     */
    public String getErrorMessage() {
        return errorMessage;
    }
	
}

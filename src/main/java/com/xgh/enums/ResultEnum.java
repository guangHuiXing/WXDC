package com.xgh.enums;

/**
 * Created by XGH on 2018/10/28
 */
public enum ResultEnum {
    CARTEMPTY(999,"购物车为空"),
    PARAMTER_ERROR(1000,"参数不正确"),
    PRODUCT_NOT_EXIST(1001,"商品不存在"),
    PRODUCT_STOCK_ERROR(1002,"库存不足"),
    ORDER_NOT_EXIST(1003,"订单信息不存在"),
    ORDERDETAIL_NOT_EXIST(1004,"订单详情库不存在"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

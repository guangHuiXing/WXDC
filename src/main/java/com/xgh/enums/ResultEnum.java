package com.xgh.enums;

/**
 * Created by XGH on 2018/10/28
 */
public enum ResultEnum {
    PRODUCT_NOT_EXIST(1001,"商品不存在"),
    PRODUCT_STOCK_ERROR(1002,"库存不足");

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

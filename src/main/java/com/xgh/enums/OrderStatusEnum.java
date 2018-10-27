package com.xgh.enums;

/**
 * Created by XGH on 2018/10/26
 */
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISH(1,"完结"),
    CANCEL(2,"已取消")
    ;
    private Integer orderCode;
    private String orderMsg;

    public Integer getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderMsg() {
        return orderMsg;
    }

    public void setOrderMsg(String orderMsg) {
        this.orderMsg = orderMsg;
    }

    OrderStatusEnum(Integer orderCode, String orderMsg) {
        this.orderCode = orderCode;
        this.orderMsg = orderMsg;
    }
}

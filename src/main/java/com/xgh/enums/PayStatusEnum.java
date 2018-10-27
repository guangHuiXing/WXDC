package com.xgh.enums;

/**
 * Created by XGH on 2018/10/26
 */
public enum PayStatusEnum {

    WAIT(0,"未支付"),
    SUCCESS(1,"已支付");
    private Integer payCode;
    private  String payMsg;

    public Integer getPayCode() {
        return payCode;
    }

    public void setPayCode(Integer payCode) {
        this.payCode = payCode;
    }

    public String getPayMsg() {
        return payMsg;
    }

    public void setPayMsg(String payMsg) {
        this.payMsg = payMsg;
    }

    PayStatusEnum(Integer payCode, String payMsg) {
        this.payCode = payCode;
        this.payMsg = payMsg;
    }
}

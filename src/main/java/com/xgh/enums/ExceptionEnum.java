package com.xgh.enums;

/**
 * Created by XGH on 2018/10/26
 */
public enum ExceptionEnum {
    UNKNOW_ERROR(-1,"未知错误"),
    USER_NOT_EXIT(-2,"用户不存在"),
    ;
    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

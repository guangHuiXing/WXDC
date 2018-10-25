package com.xgh.VO;

/**
 * Http 请求返回的最外层格式对象
 * Created by XGH on 2018/10/25
 */
public class ResultVO<T> {
    /**错误码*/
    private Integer code;
    /**返回信息*/
    private String message;
    /**具体对象*/
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

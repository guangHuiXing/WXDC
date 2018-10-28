package com.xgh.exception;

import com.xgh.enums.ResultEnum;

/**
 * Created by XGH on 2018/10/28
 */
public class SellException extends RuntimeException  {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message){
        super(message);
        this.code = code;
    }
}

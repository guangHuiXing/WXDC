package com.xgh.controller;


import com.xgh.VO.ResultVO;
import com.xgh.enums.ExceptionEnum;
import com.xgh.exception.DescribeException;
import com.xgh.utils.ResultVOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by XGH on 2018/10/26
 */
@ControllerAdvice
public class GlobleExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(GlobleExceptionHandler.class);

    /**
     * 判断错误是否是已定义的已知错误，不是则由未知错误代替，同时记录在log中
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO exceptionGet(Exception e) {
        if (e instanceof DescribeException) {
            DescribeException MyException = (DescribeException) e;
            return ResultVOUtil.error(MyException.getCode(), MyException.getMessage());
        }

        LOGGER.error("【系统异常】{}", e);
        return ResultVOUtil.error(ExceptionEnum.UNKNOW_ERROR);
    }


}



package com.xgh.utils;

import com.xgh.VO.ResultVO;
import com.xgh.enums.ExceptionEnum;

/**
 * Created by XGH on 2018/10/25
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        resultVO.setData(object);
        return resultVO;
    }
    public static ResultVO success(){
        return success(null);
    }
    public static ResultVO error(Integer code,String message,Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO error(Integer code,String message){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        resultVO.setData(null);
        return resultVO;
    }
    /***
     * 返回异常信息在已知的范围内
     * @param exceptionEnum
     *
     */
    public static ResultVO error(ExceptionEnum exceptionEnum){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(exceptionEnum.getCode());
        resultVO.setMessage(exceptionEnum.getMsg());
        resultVO.setData(null);
        return  resultVO;
    }
}

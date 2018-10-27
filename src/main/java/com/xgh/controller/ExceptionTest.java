package com.xgh.controller;

/**
 * Created by XGH on 2018/10/26
 */

import com.xgh.VO.ResultVO;
import com.xgh.dataobject.ProductInfo;
import com.xgh.enums.ExceptionEnum;
import com.xgh.utils.ResultVOUtil;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exceptionTest")
public class ExceptionTest {

    @Autowired
    private GlobleExceptionHandler exceptionHandle;

    /**
     * 返回体测试
     * @param name
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/getResult",method = RequestMethod.POST)
    public ResultVO getResult(@RequestParam("name") String name, @RequestParam("pwd") String pwd){
        ResultVO result = ResultVOUtil.success();
        try {
            if (name.equals("zzp")){
                result =  ResultVOUtil.success(new ProductInfo());
            }else if (name.equals("pzz")){
                result =  ResultVOUtil.error(ExceptionEnum.USER_NOT_EXIT);
            }else{
                int i = 1/0;
            }
        }catch (Exception e){
            result =  exceptionHandle.exceptionGet(e);
        }
        return result;
    }


}
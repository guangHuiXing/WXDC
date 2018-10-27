package com.xgh.utils;

import com.xgh.VO.ResultVO;
import com.xgh.controller.GlobleExceptionHandler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by XGH on 2018/10/26
 */
@Aspect
@Component
public class HttpAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);
    @Autowired
    private GlobleExceptionHandler globleExceptionHandler;

    @Pointcut("execution(public * com.xgh.controller.*.*(..))")
    public void log(){

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
        LOGGER.info("url={}",request.getRequestURL());
        //method
        LOGGER.info("method={}",request.getMethod());
        //ip
        LOGGER.info("id={}",request.getRemoteAddr());
        //class_method
        LOGGER.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName() + "," + joinPoint.getSignature().getName());
        //args[]
        LOGGER.info("args={}",joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "log()",returning = "object")//打印输出结果
    public void doAfterReturing(Object object){
        LOGGER.info("response={}",object.toString());
    }
    @Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ResultVO result = null;
        try {

        } catch (Exception e) {
            return globleExceptionHandler.exceptionGet(e);
        }
        if(result == null){
            return proceedingJoinPoint.proceed();
        }else {
            return result;
        }
    }

}

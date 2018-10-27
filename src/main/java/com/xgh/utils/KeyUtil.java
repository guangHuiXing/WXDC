package com.xgh.utils;

import java.util.Random;

/**
 * Created by XGH on 2018/10/28
 */
public class KeyUtil {
    /***
     * 生成唯一主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey(){

        Random random = new Random();
        Integer a = random.nextInt(99999)+100000;
        return System.currentTimeMillis()+String.valueOf(a);
    }
}

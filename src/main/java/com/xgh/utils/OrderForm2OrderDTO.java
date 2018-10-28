package com.xgh.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xgh.DTO.OrderDTO;
import com.xgh.dataobject.OrderDetail;
import com.xgh.enums.ResultEnum;
import com.xgh.exception.SellException;
import com.xgh.form.OrderForm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XGH on 2018/10/28
 */

@Slf4j
public class OrderForm2OrderDTO  {
    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        List<OrderDetail> orderDetails = new ArrayList<>();
        try{
            orderDetails = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e) {
            log.error("[对象装换错误] ，String={}",orderForm.getItems());
            throw  new SellException(ResultEnum.PARAMTER_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }
}

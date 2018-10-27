package com.xgh.service;

import com.xgh.DTO.OrderDTO;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by XGH on 2018/10/28
 */
public interface OrderService {

    /**创建订单*/
    OrderDTO create(OrderDTO orderDTO);
    /**查询单个订单*/
    OrderDTO findOne(String orderId);
    /**查询订单列表*/
    List<OrderDTO> findList(String BuyerOpenid, Pageable pageable);

    /**取消订单*/
    OrderDTO cancel(OrderDTO orderDTO);

    /**完结订单*/
    OrderDTO finish(OrderDTO orderDTO);
    /**支付订单*/
    OrderDTO paid(OrderDTO orderDTO);
}

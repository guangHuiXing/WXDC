package com.xgh.service.impl;

import com.xgh.DTO.CartDTO;
import com.xgh.DTO.OrderDTO;
import com.xgh.dataobject.OrderDetail;
import com.xgh.dataobject.OrderMaster;
import com.xgh.dataobject.ProductInfo;
import com.xgh.enums.OrderStatusEnum;
import com.xgh.enums.PayStatusEnum;
import com.xgh.enums.ResultEnum;
import com.xgh.exception.SellException;
import com.xgh.repository.OrderDetailRepository;
import com.xgh.repository.OrderMasterRepository;
import com.xgh.service.OrderService;
import com.xgh.service.ProductInfoService;
import com.xgh.utils.KeyUtil;
import com.xgh.utils.OrderMaster2OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by XGH on 2018/10/28
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderMasterRepository orderMasterRepository;
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);
        //1,查询商品（数量，价格）
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo =  productInfoService.findById(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2,计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //生成订单ID,订单详情入库
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);

            orderDetailRepository.save(orderDetail);
        }

        //3,写入订单数据库，（orderMaster,OrderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getOrderCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getPayCode());
        orderMasterRepository.save(orderMaster);
        //4,扣库存
        List<CartDTO> cartDTOS = orderDTO.getOrderDetailList().stream().map(e->
                new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOS);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        Optional<OrderMaster> orderMaster = orderMasterRepository.findById(orderId);
        if(!orderMaster.isPresent()){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        OrderMaster orderMaster1 = orderMaster.get();
        List<OrderDetail> orderDetail = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetail)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster1,orderDTO);
        orderDTO.setOrderDetailList(orderDetail);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> page = orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(
                OrderMaster2OrderDTO.convert(page.getContent())
                ,pageable,page.getTotalElements());
        return orderDTOPage;

    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        //判断订单状态

        if(orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getOrderCode())){

        }
        // 修改订单状态
        //返还库存
        //如果已支付，需要退款

        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}

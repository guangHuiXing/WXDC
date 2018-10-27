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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by XGH on 2018/10/28
 */
@Service
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
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
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
        return null;
    }

    @Override
    public List<OrderDTO> findList(String BuyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
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

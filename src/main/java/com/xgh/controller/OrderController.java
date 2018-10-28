package com.xgh.controller;

import com.xgh.DTO.OrderDTO;
import com.xgh.VO.ResultVO;
import com.xgh.enums.ResultEnum;
import com.xgh.exception.SellException;
import com.xgh.form.OrderForm;
import com.xgh.service.OrderService;
import com.xgh.utils.OrderForm2OrderDTO;
import com.xgh.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XGH on 2018/10/28
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;
    //创建订单
    @RequestMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error("[ 创建订单，参数不正确]，orderForm={}",orderForm);
           throw new SellException(ResultEnum.PARAMTER_ERROR.getCode()
                   ,bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[创建订单购物车不能为空]");
            throw new SellException(ResultEnum.CARTEMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String ,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        log.info("[创建订单结束]");
        return ResultVOUtil.success(map);
    }

    @GetMapping(value = "/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid")String openid,
                                         @RequestParam(value = "page",defaultValue = "0")Integer page,
                                         @RequestParam(value = "size",defaultValue = "10")Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("[openId为空]");
            throw new SellException(ResultEnum.PARAMTER_ERROR);
        }
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<OrderDTO> orderDTOPage =  orderService.findList(openid,pageRequest);
        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    /**
     * 订单详情
     */
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openId,
                                     @RequestParam("orderId") String orderId){
        //TODO
        OrderDTO orderDTO = orderService.findOne(orderId);
        return ResultVOUtil.success(orderDTO);

    }
}

package com.xgh.service.impl;

import com.xgh.DTO.CartDTO;
import com.xgh.dataobject.ProductInfo;
import com.xgh.enums.ProductStatusEnum;
import com.xgh.enums.ResultEnum;
import com.xgh.exception.SellException;
import com.xgh.repository.ProductInfoRepository;
import com.xgh.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by XGH on 2018/10/25
 */

@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public ProductInfo findById(String productId) {
        Optional<ProductInfo> productInfo = productInfoRepository.findById(productId);
        if(productInfo.isPresent()){
            return productInfo.get();
        }else {
            log.error("[查询产品信息为空，productId={}]",productId);
            return null;
        }





    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOS) {

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOS) {
        for(CartDTO cartDTO :cartDTOS){
           Optional<ProductInfo> productInfo1 = productInfoRepository.findById(cartDTO.getProductId());
            if(!productInfo1.isPresent()){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfo1.get();
            Integer result = productInfo.getProductStock()-cartDTO.getProductQuantity();
            if(result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }

}
}

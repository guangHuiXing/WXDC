package com.xgh.service.impl;

import com.xgh.DTO.CartDTO;
import com.xgh.dataobject.ProductInfo;
import com.xgh.enums.ProductStatusEnum;
import com.xgh.enums.ResultEnum;
import com.xgh.exception.SellException;
import com.xgh.repository.ProductInfoRepository;
import com.xgh.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by XGH on 2018/10/25
 */

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public ProductInfo findById(String productId) {
        return productInfoRepository.findById(productId).get();
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
           ProductInfo productInfo = productInfoRepository.findById(cartDTO.getProductId()).get();
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock()-cartDTO.getProductQuantity();
            if(result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }

}
}

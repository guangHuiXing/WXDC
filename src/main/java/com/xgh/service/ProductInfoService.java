package com.xgh.service;

import com.xgh.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 * Created by XGH on 2018/10/25
 */
public interface ProductInfoService {
    ProductInfo findById(String productId);

    /*
    查询上架的商品列表
     */
    List<ProductInfo> findUpAll();
    /**
     * 查询所有的商品列表
     */
    Page<ProductInfo> findAll(Pageable pageable);
    /**
     * 保存
     */
    ProductInfo save(ProductInfo productInfo);
    //加库存
    //减库存
}

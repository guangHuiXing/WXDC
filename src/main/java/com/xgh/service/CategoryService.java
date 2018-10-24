package com.xgh.service;

import com.xgh.dataobject.ProductCategory;

import java.util.List;

/**
 * Created by XGH on 2018/10/24
 */
public interface CategoryService {

    ProductCategory findOne(Integer id);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}

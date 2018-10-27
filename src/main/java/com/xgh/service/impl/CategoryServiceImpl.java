package com.xgh.service.impl;

import com.xgh.dataobject.ProductCategory;
import com.xgh.repository.ProductCategoryRepository;
import com.xgh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by XGH on 2018/10/24
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}

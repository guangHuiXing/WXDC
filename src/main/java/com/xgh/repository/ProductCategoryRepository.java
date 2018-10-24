package com.xgh.repository;

import com.xgh.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XGH on 2018/10/24
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
}

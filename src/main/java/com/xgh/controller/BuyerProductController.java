package com.xgh.controller;

import com.xgh.VO.ProductInfoVO;
import com.xgh.VO.ProductVO;
import com.xgh.VO.ResultVO;
import com.xgh.dataobject.ProductCategory;
import com.xgh.dataobject.ProductInfo;
import com.xgh.service.CategoryService;
import com.xgh.service.ProductInfoService;
import com.xgh.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 买家商品
 * Created by XGH on 2018/10/25
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ResultVO list(){
        //1.查询所有上架的商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //2.查询类目（一次性查询）
        List<Integer> categoryTypeList = new ArrayList<>();
        //传统方法
        for(ProductInfo productInfo:productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }
        //精简方法（java8 lambda）
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType() == productCategory.getCategoryType()){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        //3.数据拼装

        return  ResultVOUtil.success(productVOList);
    }
}

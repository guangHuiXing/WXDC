package com.xgh.DTO;

/**
 * Created by XGH on 2018/10/28
 */
public class CartDTO {
    /**商品ID*/
    private String productId;
    /**商品数量*/
    private Integer productQuantity;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}

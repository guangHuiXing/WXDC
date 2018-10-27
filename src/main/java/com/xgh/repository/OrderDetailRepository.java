package com.xgh.repository;

import com.xgh.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XGH on 2018/10/28
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
}

package com.merrill.dao.mapper;

import com.merrill.dao.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-02-16
 * Time: 11:43
 * Description:
 */
@Repository
public interface OrderMapper {
    int saveOrder(@Param("id") Long id, @Param("phone") String phone,
                  @Param("location") String location, @Param("repairment") String result,
                  @Param("userDescription") String userDescription);

    Order getOrderByUserIDAndStatus(@Param("id") Long id, @Param("status") long status);
}

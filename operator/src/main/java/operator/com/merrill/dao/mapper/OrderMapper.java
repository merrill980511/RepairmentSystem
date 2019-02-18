package operator.com.merrill.dao.mapper;

import operator.com.merrill.query.OrderQueryObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-02-18
 * Time: 10:11
 * Description:
 */
@Repository
public interface OrderMapper {
    List<?> getOrderList(OrderQueryObject qo);

    List<?> getOrderFinishedList(OrderQueryObject qo);

    int takeOrder(@Param("operatorID") Long operatorID, @Param("orderID") Long orderID,
                  @Param("status") int status);
}

package operator.com.merrill.service;

import com.github.pagehelper.PageInfo;
import operator.com.merrill.query.OrderQueryObject;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-02-18
 * Time: 10:10
 * Description:
 */
public interface IOrderService {
    PageInfo getOrderList(OrderQueryObject qo);

    PageInfo getOrderFinishedList(OrderQueryObject qo);

    boolean takeOrder(Long operatorID, Long orderID);
}

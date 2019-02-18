package user.com.merrill.service;

import user.com.merrill.dao.entity.Order;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-02-16
 * Time: 11:44
 * Description:
 */
public interface IOrderService {
    boolean saveOrder(Long id, String phone, String repairment, String location, String userDescription);

    Order getOrderByUserID(Long id);

    boolean finishOrder(Long id);
}

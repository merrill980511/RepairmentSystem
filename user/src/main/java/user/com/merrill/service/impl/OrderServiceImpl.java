package user.com.merrill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.com.merrill.dao.entity.Order;
import user.com.merrill.dao.entity.User;
import user.com.merrill.dao.mapper.OrderMapper;
import user.com.merrill.dao.mapper.UserMapper;
import user.com.merrill.service.IOrderService;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-02-16
 * Time: 11:44
 * Description:
 */
@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean saveOrder(Long id, String phone, String repairment, String location, String userDescription) {
        User user = userMapper.getUserByID(id);
        if(orderMapper.saveOrder(id, user.getPhone(), location, repairment, userDescription) > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrderByUserID(Long id) {
        return orderMapper.getOrderByUserID(id);
    }

    @Override
    public boolean finishOrder(Long id) {
        Order order = orderMapper.getOrderByID(id);
        if (orderMapper.deleteOrderByID(id) <= 0){
            return false;
        }
        if (order.getOperator() == null){
            if (orderMapper.saveFinishedOrder(order.getId(), order.getUser().getId(),
                    null, order.getLocation(), order.getPhone(),
                    order.getBeginTime(), order.getUserDescription(), order.getDescription(),
                    order.getRepairment(), 2) <= 0){
                return false;
            }
        } else {
            if (orderMapper.saveFinishedOrder(order.getId(), order.getUser().getId(),
                    order.getOperator().getId(), order.getLocation(), order.getPhone(),
                    order.getBeginTime(), order.getUserDescription(), order.getDescription(),
                    order.getRepairment(), 2) <= 0){
                return false;
            }
        }
        return true;
    }
}

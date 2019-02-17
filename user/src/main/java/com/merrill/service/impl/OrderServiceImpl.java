package com.merrill.service.impl;

import com.merrill.dao.entity.Order;
import com.merrill.dao.entity.User;
import com.merrill.dao.mapper.OrderMapper;
import com.merrill.dao.mapper.UserMapper;
import com.merrill.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-02-16
 * Time: 11:44
 * Description:
 */
@Service
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
    public Order getOrderByUserID(Long id) {
        return orderMapper.getOrderByUserIDAndStatus(id, 0l);
    }
}

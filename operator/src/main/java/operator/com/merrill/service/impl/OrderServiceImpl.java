package operator.com.merrill.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import operator.com.merrill.dao.mapper.OrderMapper;
import operator.com.merrill.query.OrderQueryObject;
import operator.com.merrill.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-02-18
 * Time: 10:10
 * Description:
 */
@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional(readOnly = true)
    public PageInfo getOrderList(OrderQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<?> list = orderMapper.getOrderList(qo);
        PageInfo pageInfo = new PageInfo(list);
        if (pageInfo.getPages() <= 0) {
            pageInfo.setPages(1);
        }
        return pageInfo;
    }

    @Override
    @Transactional(readOnly = true)
    public PageInfo getOrderFinishedList(OrderQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<?> list = orderMapper.getOrderFinishedList(qo);
        PageInfo pageInfo = new PageInfo(list);
        if (pageInfo.getPages() <= 0) {
            pageInfo.setPages(1);
        }
        return pageInfo;
    }

    @Override
    public boolean takeOrder(Long operatorID, Long orderID) {
        if (orderMapper.takeOrder(operatorID, orderID, 1) > 0) {
            return true;
        }
        return false;
    }
}

package com.iyqrj.starmall.service.impl;

import com.iyqrj.starmall.entity.Order;
import com.iyqrj.starmall.mapper.OrderMapper;
import com.iyqrj.starmall.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单总表 服务实现类
 * </p>
 *
 * @author lrj
 * @since 2020-06-15
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}

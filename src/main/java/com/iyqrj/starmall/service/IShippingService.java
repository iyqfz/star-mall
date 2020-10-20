package com.iyqrj.starmall.service;

import com.github.pagehelper.PageInfo;
import com.iyqrj.starmall.common.ServerResponse;
import com.iyqrj.starmall.entity.Shipping;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lrj
 * @since 2020-10-19
 */
public interface IShippingService extends IService<Shipping> {

    ServerResponse add(Integer userId, Shipping shipping);

    ServerResponse<String> del(Integer userId, Integer shippingId);

    ServerResponse update(Integer userId, Shipping shipping);

    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}

package com.iyqrj.starmall.service;

import com.github.pagehelper.PageInfo;
import com.iyqrj.starmall.common.ServerResponse;
import com.iyqrj.starmall.entity.CustomerService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lrj
 * @since 2020-10-19
 */
public interface ICustomerServiceService{

    ServerResponse<String> add(Integer userId, Long orderNo, String title, String mainContent);

    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);

    ServerResponse<String> deleteById(Integer userId, Integer id, Long orderNo);

    ServerResponse<String> update(Integer userId, CustomerService customerService);

    //    backend
    ServerResponse<PageInfo> listAll(int pageNum, int pageSize);

    ServerResponse<PageInfo> searchCustomerService(Integer id, String username, String orderNo, int pageNum, int pageSize);

    ServerResponse<CustomerService> get(Integer csId);

    ServerResponse<CustomerService> replyService(CustomerService customerService);
}

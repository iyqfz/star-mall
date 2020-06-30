package com.iyqrj.starmall.service;

import com.iyqrj.starmall.common.Result;
import com.iyqrj.starmall.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author lrj
 * @since 2020-06-17
 */
public interface IAdminService extends IService<Admin> {

    Result add(Admin admin);

    Result list(Integer pageIndex, Integer pageSize, String searchUsername, String searchEmail);

    Result login(String username, String password);
}

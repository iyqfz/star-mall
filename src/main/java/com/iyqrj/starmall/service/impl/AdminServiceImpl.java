package com.iyqrj.starmall.service.impl;

import com.iyqrj.starmall.entity.Admin;
import com.iyqrj.starmall.mapper.AdminMapper;
import com.iyqrj.starmall.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author lrj
 * @since 2020-06-15
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}

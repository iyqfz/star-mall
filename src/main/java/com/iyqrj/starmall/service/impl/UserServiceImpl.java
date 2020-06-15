package com.iyqrj.starmall.service.impl;

import com.iyqrj.starmall.entity.User;
import com.iyqrj.starmall.mapper.UserMapper;
import com.iyqrj.starmall.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lrj
 * @since 2020-06-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

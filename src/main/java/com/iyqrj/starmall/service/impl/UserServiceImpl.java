package com.iyqrj.starmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iyqrj.starmall.common.Result;
import com.iyqrj.starmall.entity.User;
import com.iyqrj.starmall.mapper.UserMapper;
import com.iyqrj.starmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lrj
 * @since 2020-06-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result login(String username, String password) {
        QueryWrapper<User> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.and(wrapper -> wrapper.eq("username", username).or().eq("email", username));
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        adminQueryWrapper.eq("password", md5Password);
        User user = userMapper.selectOne(adminQueryWrapper);
        if(null == user){
            return Result.error("用户名或密码错误");
        } else {
            user.setPassword(null);
            return Result.ok("登录成功", user);
        }
    }
}

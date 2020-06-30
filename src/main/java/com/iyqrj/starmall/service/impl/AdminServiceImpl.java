package com.iyqrj.starmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iyqrj.starmall.common.Result;
import com.iyqrj.starmall.entity.Admin;
import com.iyqrj.starmall.mapper.AdminMapper;
import com.iyqrj.starmall.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author lrj
 * @since 2020-06-17
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Result add(Admin admin) {
        if(StringUtils.isEmpty(admin.getUsername())) {
            return Result.error("用户名不能为空");
        }
        if(StringUtils.isEmpty(admin.getPassword())) {
            return Result.error("密码不能为空");
        }

        QueryWrapper<Admin> usernameQueryWrapper = new QueryWrapper<>();
        usernameQueryWrapper.eq("username", admin.getUsername());
        usernameQueryWrapper.or();
        usernameQueryWrapper.eq("email", admin.getUsername());
        if(null != adminMapper.selectOne(usernameQueryWrapper)){
            return Result.error("该用户名已存在");
        }

        if(StringUtils.isNotEmpty(admin.getEmail())) {
            QueryWrapper<Admin> emailQueryWrapper = new QueryWrapper<>();
            emailQueryWrapper.eq("email", admin.getEmail());
            emailQueryWrapper.or();
            emailQueryWrapper.eq("username", admin.getEmail());
            if(null != adminMapper.selectOne(emailQueryWrapper)){
                return Result.error("该邮箱已存在");
            }
        } else {
            admin.setEmail(null);
        }

        String md5Password = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
        admin.setPassword(md5Password);
        adminMapper.insert(admin);
        return Result.ok("添加成功");
    }

    @Override
    public Result list(Integer pageIndex, Integer pageSize, String searchUsername, String searchEmail) {
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.select(Admin.class, info -> !info.getColumn().equals("password"))
                        .like(StringUtils.isNotEmpty(searchUsername), "username", searchUsername)
                        .like(StringUtils.isNotEmpty(searchEmail), "email", searchEmail);

        Page<Admin> adminPage = new Page<>(pageIndex, pageSize);
        IPage<Admin> adminIPage = adminMapper.selectPage(adminPage, adminQueryWrapper);
        return Result.ok(adminIPage);
    }

    @Override
    public Result login(String username, String password) {
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.select(Admin.class, info -> !info.getColumn().equals("password"))
                .and(wrapper -> wrapper.eq("username", username).or().eq("email", username));
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        adminQueryWrapper.eq("password", md5Password);
        Admin admin = adminMapper.selectOne(adminQueryWrapper);
        if(null == admin){
            return Result.error("用户名或密码错误");
        } else {
            return Result.ok("登录成功", admin);
        }
    }
}

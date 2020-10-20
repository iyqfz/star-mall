package com.iyqrj.starmall.service;

import com.github.pagehelper.PageInfo;
import com.iyqrj.starmall.common.ServerResponse;
import com.iyqrj.starmall.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iyqrj.starmall.vo.UserPointVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lrj
 * @since 2020-10-19
 */
public interface IUserService extends IService<User> {

    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<String> selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username, String question, String answer);

    ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<UserPointVo> getInformation(Integer id);

    ServerResponse checkAdminRole(User user);

    //backend
    ServerResponse<PageInfo> list(int pageNum, int pageSize);

    ServerResponse<PageInfo> searchUser(Integer userId, String username, String phone, String roleName, int pageNum, int pageSize);

    ServerResponse<String> edit(User user);

    ServerResponse<User> getUser(Integer userId);

    ServerResponse<User> addUserAdmin(User user);
}

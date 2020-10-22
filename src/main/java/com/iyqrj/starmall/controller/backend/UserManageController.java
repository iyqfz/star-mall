package com.iyqrj.starmall.controller.backend;

import com.github.pagehelper.PageInfo;
import com.iyqrj.starmall.common.Const;
import com.iyqrj.starmall.common.ResponseCode;
import com.iyqrj.starmall.common.ServerResponse;
import com.iyqrj.starmall.entity.User;
import com.iyqrj.starmall.service.IShippingService;
import com.iyqrj.starmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by UsingLove on 2017/8/12.
 */
@Controller
@RequestMapping(value = "/manage/user/")
public class UserManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IShippingService iShippingService;

    /**
     * 后台用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username, password);
        if(response.isSuccess()) {
            User user = response.getData();
            if(user.getRole() == Const.Role.ROLE_ADMIN) {
                session.setAttribute(Const.CURRENT_USER,user);
                return response;
            } else {
                return ServerResponse.createByErrorMessage("不是管理员，无法登录");
            }
        }
        return response;
    }

    /**
     * 显示所有用户
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> list(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加产品的业务逻辑
            return iUserService.list(pageNum,pageSize);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 分页查询用户
     * @param session
     * @param pageNum
     * @param pageSize
     * @param userId
     * @param username
     * @param phone
     * @param roleName
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> search(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Integer userId, String username,
                                           String phone, String roleName){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加产品的业务逻辑
            return iUserService.searchUser(userId,username,phone,roleName,pageNum,pageSize);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 编辑用户
     * @param session
     * @param user
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> edit(HttpSession session, User user){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(currentUser).isSuccess()){
            //填充我们增加产品的业务逻辑
            return iUserService.edit(user);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 根据id获取用户基本信息
     * @param session
     * @param userId
     * @return
     */
    @RequestMapping(value = "get")
    @ResponseBody
    public ServerResponse<User> edit(HttpSession session, Integer userId){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(currentUser).isSuccess()){
            //填充我们增加产品的业务逻辑
            return iUserService.getUser(userId);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 添加管理员
     * @param session
     * @param user
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> add(HttpSession session, User user){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(currentUser).isSuccess()){
            //填充我们增加产品的业务逻辑
            return iUserService.addUserAdmin(user);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 分页查看所有收货地址
     * @param pageNum
     * @param pageSize
     * @param session
     * @return
     */
    @RequestMapping("shipping_list")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                                         Integer userId,
                                         HttpSession session){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(currentUser).isSuccess()){
            //填充我们增加产品的业务逻辑
            return iShippingService.list(userId,pageNum,pageSize);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }
}

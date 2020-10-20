package com.iyqrj.starmall.controller.backend;

import com.github.pagehelper.PageInfo;
import com.iyqrj.starmall.common.Const;
import com.iyqrj.starmall.common.ResponseCode;
import com.iyqrj.starmall.common.ServerResponse;
import com.iyqrj.starmall.entity.CustomerService;
import com.iyqrj.starmall.entity.User;
import com.iyqrj.starmall.service.ICustomerServiceService;
import com.iyqrj.starmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/12/3.
 */
@Controller
@RequestMapping("/manage/customer_service/")
public class CustomerServiceManageController {

    @Autowired
    private ICustomerServiceService iCustomerServiceService;
    @Autowired
    private IUserService iUserService;

    /**
     * 显示所有售后信息
     * @param session
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ServerResponse<PageInfo> list(HttpSession session,
                                         @RequestParam(value = "pageNum", defaultValue= "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //更新categoryName
            return iCustomerServiceService.listAll(pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    /**
     * 分页查找服务
     * @param session
     * @param pageNum
     * @param pageSize
     * @param id
     * @param username
     * @param orderNo
     * @return
     */
    @RequestMapping(value = "search")
    @ResponseBody
    public ServerResponse<PageInfo> search(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                           Integer id,
                                           String username,
                                           String orderNo){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加产品的业务逻辑
            return iCustomerServiceService.searchCustomerService(id,username,orderNo,pageNum,pageSize);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 获取服务
     * @param session
     * @param csId
     * @return
     */
    @RequestMapping(value = "get")
    @ResponseBody
    public ServerResponse<CustomerService> get(HttpSession session, Integer csId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加产品的业务逻辑
            return iCustomerServiceService.get(csId);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 回复服务
     * @param session
     * @param customerService
     * @return
     */
    @RequestMapping(value = "reply_service")
    @ResponseBody
    public ServerResponse<CustomerService> replyService(HttpSession session, CustomerService customerService){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加产品的业务逻辑
            return iCustomerServiceService.replyService(customerService);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }
}

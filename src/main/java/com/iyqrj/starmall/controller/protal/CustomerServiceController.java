package com.iyqrj.starmall.controller.protal;


import com.github.pagehelper.PageInfo;
import com.iyqrj.starmall.common.Const;
import com.iyqrj.starmall.common.ResponseCode;
import com.iyqrj.starmall.common.ServerResponse;
import com.iyqrj.starmall.entity.CustomerService;
import com.iyqrj.starmall.entity.User;
import com.iyqrj.starmall.service.ICustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lrj
 * @since 2020-10-19
 */
@RestController
@RequestMapping("/customer-service")
public class CustomerServiceController {

    @Autowired
    private ICustomerServiceService iCustomerServiceService;

    /**
     * 添加售后
     * @param session
     * @param orderNo
     * @param title
     * @param mainContent
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ServerResponse<String> add(HttpSession session, Long orderNo, String title, String mainContent) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCustomerServiceService.add(user.getId(), orderNo, title, mainContent);
    }

    /**
     * 显示所有售后信息
     * @param session
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ServerResponse<PageInfo> list(HttpSession session, @RequestParam(value = "pageNum", defaultValue= "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCustomerServiceService.list(user.getId(), pageNum, pageSize);
    }

    /**
     * 删除单个服务
     * @param session
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ServerResponse<String> delete(HttpSession session, Integer id, Long orderNo) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCustomerServiceService.deleteById(user.getId(), id, orderNo);
    }

    /**
     * 更新服务
     * @param session
     * @param customerService
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ServerResponse<String> update(HttpSession session, CustomerService customerService) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCustomerServiceService.update(user.getId(), customerService);
    }

}
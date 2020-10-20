package com.iyqrj.starmall.controller.protal;


import com.iyqrj.starmall.common.Const;
import com.iyqrj.starmall.common.ResponseCode;
import com.iyqrj.starmall.common.ServerResponse;
import com.iyqrj.starmall.entity.User;
import com.iyqrj.starmall.service.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/level")
public class LevelController {

    @Autowired
    private ILevelService iLevelService;

    /**
     * 获取用户星级
     * @param session
     * @return
     */
    @RequestMapping("get_user_level")
    @ResponseBody
    public ServerResponse<String> getUserLevel(HttpSession session) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iLevelService.getUserLevel(user.getId());
    }
}


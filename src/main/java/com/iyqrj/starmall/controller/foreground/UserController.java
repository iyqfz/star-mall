package com.iyqrj.starmall.controller.foreground;


import com.iyqrj.starmall.common.Result;
import com.iyqrj.starmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lrj
 * @since 2020-06-17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService iUserService;
    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @ResponseBody
    @PostMapping(value = "login")
    public Result login(HttpSession session,
                        @RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password){
        Result result = iUserService.login(username, password);
        if(result.getSuccess()) {
            session.setAttribute("loginUser", result.getResult());
        }
        return result;
    }
}


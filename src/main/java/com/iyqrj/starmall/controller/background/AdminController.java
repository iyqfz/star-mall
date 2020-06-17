package com.iyqrj.starmall.controller.background;


import com.iyqrj.starmall.common.Result;
import com.iyqrj.starmall.entity.Admin;
import com.iyqrj.starmall.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author lrj
 * @since 2020-06-17
 */
@RestController
@RequestMapping("/manage/admin")
public class AdminController {

    private final IAdminService iAdminService;
    @Autowired
    public AdminController(IAdminService iAdminService) {
        this.iAdminService = iAdminService;
    }

    @ResponseBody
    @RequestMapping(value = "add")
    public Result add(HttpSession session, @Validated Admin admin){
        Admin loginAdmin = (Admin)session.getAttribute("loginAdmin");
        if(null == loginAdmin){
            return Result.error("未登录，请登录管理员");
        }
        Result result = iAdminService.add(admin);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public Result delete(HttpSession session, @RequestParam(value = "id", required = true) Integer id){
        Admin loginAdmin = (Admin)session.getAttribute("loginAdmin");
        if(null == loginAdmin){
            return Result.error("未登录，请登录管理员");
        }
        iAdminService.removeById(id);
        return Result.ok();
    }

    @ResponseBody
    @RequestMapping(value = "update")
    public Result update(HttpSession session, Admin admin){
        Admin loginAdmin = (Admin)session.getAttribute("loginAdmin");
        if(null == loginAdmin){
            return Result.error("未登录，请登录管理员");
        }
        iAdminService.updateById(admin);
        return Result.ok();
    }

    @ResponseBody
    @GetMapping(value = "list")
    public Result list(HttpSession session,
                       @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                       @RequestParam(value = "username", required = false) String username){
        Admin loginAdmin = (Admin)session.getAttribute("loginAdmin");
        if(null == loginAdmin){
            return Result.error("未登录，请登录管理员");
        }
        Result result = iAdminService.list(pageIndex, pageSize, username);
        return result;
    }

    @ResponseBody
    @PostMapping(value = "login")
    public Result login(HttpSession session,
                        @RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password){
        Result result = iAdminService.login(username, password);
        if(result.getSuccess()) {
            session.setAttribute("loginAdmin", result.getResult());
        }
        return result;
    }

    @ResponseBody
    @GetMapping(value = "logout")
    public Result logout(HttpSession session){
        session.removeAttribute("loginAdmin");
        return Result.ok();
    }
}


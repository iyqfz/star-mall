package com.iyqrj.starmall.controller.background;

import com.iyqrj.starmall.common.Result;
import com.iyqrj.starmall.entity.Product;
import com.iyqrj.starmall.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manage/product")
public class ProductManageController {

    private final IProductService iProductService;
    @Autowired
    public ProductManageController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @ResponseBody
    @PostMapping(value = "add")
    public Result add(HttpSession session, Product product){
        return Result.ok();
    }

    @ResponseBody
    @GetMapping(value = "list")
    public Result list(HttpSession session,
                       @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        Product product1 = iProductService.getById(2L);
        return Result.error("失败 ");
    }
}

package com.iyqrj.starmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.iyqrj.starmall.common.ServerResponse;
import com.iyqrj.starmall.entity.Shipping;
import com.iyqrj.starmall.mapper.ShippingMapper;
import com.iyqrj.starmall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lrj
 * @since 2020-10-19
 */
@Service
public class ShippingServiceImpl implements IShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    public ServerResponse add(Integer userId, Shipping shipping){
        shipping.setUserId(userId);
        int rowCount = shippingMapper.insert(shipping);
        if(rowCount > 0) {
            Map map = Maps.newHashMap();
            map.put("shippingId", shipping.getId());
            return ServerResponse.createBySuccess("新建地址成功",map);
        }
        return ServerResponse.createByErrorMessage("新建地址失败");
    }

    //解决横向越权的问题
    public ServerResponse<String> del(Integer userId, Integer shippingId) {
        int resultCount = shippingMapper.deleteByShippingIdUserId(userId, shippingId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除地址成功");
        }
        return ServerResponse.createBySuccess("删除地址失败");
    }

    //解决横向越权的问题
    public ServerResponse update(Integer userId, Shipping shipping){
        //为什么要重新设置userId?
        //因为userId只允许从session中取得，不许从参数链接中取得
        shipping.setUserId(userId);
        int rowCount = shippingMapper.updateByShipping(shipping);
        if(rowCount > 0) {
            return ServerResponse.createBySuccess("更新地址成功");
        }
        return ServerResponse.createByErrorMessage("更新地址失败");
    }

    //解决横向越权的问题
    public ServerResponse<Shipping> select(Integer userId, Integer shippingId) {
        QueryWrapper<Shipping> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id" , shippingId);
        queryWrapper.eq("user_id", userId);
        Shipping shipping = shippingMapper.selectOne(queryWrapper);
//        Shipping shipping = shippingMapper.selectByShippingIdUserId(userId, shippingId);
        if(shipping == null) {
            return ServerResponse.createByErrorMessage("无法查询到该地址");
        }
        return ServerResponse.createBySuccess("查询地址成功",shipping);
    }

    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        QueryWrapper<Shipping> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Shipping> shippingList = shippingMapper.selectList(queryWrapper);
//        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}


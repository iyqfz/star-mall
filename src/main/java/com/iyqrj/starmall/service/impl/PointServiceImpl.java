package com.iyqrj.starmall.service.impl;

import com.iyqrj.starmall.common.ServerResponse;
import com.iyqrj.starmall.mapper.PointMapper;
import com.iyqrj.starmall.service.IPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lrj
 * @since 2020-10-19
 */
@Service
public class PointServiceImpl implements IPointService {

    @Autowired
    private PointMapper pointMapper;

    @Override
    public ServerResponse<Long> getPoint(Integer userId) {
        Long point = pointMapper.getUserPoint(userId);
        return ServerResponse.createBySuccess(point);
    }
}

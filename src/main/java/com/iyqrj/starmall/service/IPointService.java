package com.iyqrj.starmall.service;

import com.iyqrj.starmall.common.ServerResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lrj
 * @since 2020-10-19
 */
public interface IPointService {

    ServerResponse<Long> getPoint(Integer userId);
}

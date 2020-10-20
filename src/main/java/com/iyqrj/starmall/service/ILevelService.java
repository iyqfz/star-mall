package com.iyqrj.starmall.service;

import com.iyqrj.starmall.common.ServerResponse;
import com.iyqrj.starmall.entity.Level;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lrj
 * @since 2020-10-19
 */
public interface ILevelService extends IService<Level> {

    ServerResponse<String> getUserLevel(Integer userId);

}

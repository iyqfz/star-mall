package com.iyqrj.starmall.service.impl;

import com.iyqrj.starmall.common.Const;
import com.iyqrj.starmall.common.ServerResponse;
import com.iyqrj.starmall.mapper.LevelMapper;
import com.iyqrj.starmall.service.ILevelService;
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
public class LevelServiceImpl implements ILevelService {

    @Autowired
    private LevelMapper levelMapper;

    @Override
    public ServerResponse<String> getUserLevel(Integer userId) {
        int userExperience = levelMapper.getUserExperience(userId);
        if(userExperience < 5000) {
            return ServerResponse.createBySuccess(Const.UserLevel.LEVEL_ONE);
        }
        if(userExperience < 15000) {
            return ServerResponse.createBySuccess(Const.UserLevel.LEVEL_TWO);
        }
        if(userExperience < 40000) {
            return ServerResponse.createBySuccess(Const.UserLevel.LEVEL_THREE);
        }
        if(userExperience < 10000) {
            return ServerResponse.createBySuccess(Const.UserLevel.LEVEL_FOUR);
        } else {
            return ServerResponse.createBySuccess(Const.UserLevel.LEVEL_FIVE);
        }
    }
}

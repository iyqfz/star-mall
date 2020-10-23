package com.iyqrj.starmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iyqrj.starmall.entity.Level;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lrj
 * @since 2020-10-19
 */
public interface LevelMapper extends BaseMapper<Level> {

    int deleteByPrimaryKey(Integer id);

    int insert(Level record);

    int insertSelective(Level record);

    Level selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Level record);

    int updateByPrimaryKey(Level record);

    int getUserExperience(Integer userId);

    Level getByUserId(Integer userId);
}

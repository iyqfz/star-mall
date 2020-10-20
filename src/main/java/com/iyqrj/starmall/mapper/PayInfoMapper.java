package com.iyqrj.starmall.mapper;

import com.iyqrj.starmall.entity.PayInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lrj
 * @since 2020-10-19
 */
public interface PayInfoMapper extends BaseMapper<PayInfo> {
    int deleteByPrimaryKey(Integer id);

    int insert(PayInfo record);

    int insertSelective(PayInfo record);

    PayInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayInfo record);

    int updateByPrimaryKey(PayInfo record);

}

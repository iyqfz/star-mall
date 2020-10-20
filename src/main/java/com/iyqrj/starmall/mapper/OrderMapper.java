package com.iyqrj.starmall.mapper;

import com.iyqrj.starmall.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lrj
 * @since 2020-10-19
 */
public interface OrderMapper extends BaseMapper<Order> {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Order selectByUserIdAndOrderNo(@Param("userId") Integer userId, @Param("orderNo") Long orderNo);

    Order selectByOrderNo(Long orderNo);

    List<Order> selectByUserId(Integer userId);

    List<Order> selectAllOrder();

    void updateStatus(@Param("userId") Integer userId, @Param("orderNo") Long orderNo, @Param("status") int status);

    int getPaymentByOrderNo(@Param("userId") Integer userId, @Param("orderNo") Long orderNo);

    void updateStatusByOrderNo(@Param("userId") Integer userId, @Param("orderNo") Long orderNo, @Param("status") int status);

}

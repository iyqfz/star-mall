package com.iyqrj.starmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iyqrj.starmall.entity.Orders;
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
public interface OrdersMapper extends BaseMapper<Orders> {
    int deleteByPrimaryKey(Integer id);

//    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    Orders selectByUserIdAndOrderNo(@Param("userId") Integer userId, @Param("orderNo") Long orderNo);

    Orders selectByOrderNo(Long orderNo);

    List<Orders> selectByUserId(Integer userId);

    List<Orders> selectAllOrder();

    void updateStatus(@Param("userId") Integer userId, @Param("orderNo") Long orderNo, @Param("status") int status);

    int getPaymentByOrderNo(@Param("userId") Integer userId, @Param("orderNo") Long orderNo);

    void updateStatusByOrderNo(@Param("userId") Integer userId, @Param("orderNo") Long orderNo, @Param("status") int status);

}

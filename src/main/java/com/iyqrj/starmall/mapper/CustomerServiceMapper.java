package com.iyqrj.starmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iyqrj.starmall.entity.CustomerService;
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
public interface CustomerServiceMapper extends BaseMapper<CustomerService> {

    int deleteByPrimaryKey(Integer id);

//    int insert(CustomerService record);

    int insertSelective(CustomerService record);

    CustomerService selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerService record);

    int updateByPrimaryKey(CustomerService record);

    List<CustomerService> selectByUserId(Integer userId);

    void deleteByPrimaryKeyAndUserId(@Param("userId") Integer userId, @Param("id") Integer id);

    void update(CustomerService customerService);

    List<CustomerService> selectAll();

    List<CustomerService> selectbyIUO(@Param("id") Integer id, @Param("userId") Integer userId, @Param("orderNo") String orderNo);

}

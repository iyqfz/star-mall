package com.iyqrj.starmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iyqrj.starmall.entity.Category;
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
public interface CategoryMapper extends BaseMapper<Category> {

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> selectCategoryChildrenByParentId(@Param("parentId") Integer parentId);

}

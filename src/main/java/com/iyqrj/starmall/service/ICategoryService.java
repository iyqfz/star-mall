package com.iyqrj.starmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iyqrj.starmall.common.ServerResponse;
import com.iyqrj.starmall.entity.Category;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lrj
 * @since 2020-10-19
 */
public interface ICategoryService extends IService<Category> {

    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);

}

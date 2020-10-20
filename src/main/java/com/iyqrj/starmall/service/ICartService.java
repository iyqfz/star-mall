package com.iyqrj.starmall.service;

import com.iyqrj.starmall.common.ServerResponse;
import com.iyqrj.starmall.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iyqrj.starmall.vo.CartVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lrj
 * @since 2020-10-19
 */
public interface ICartService extends IService<Cart> {

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> selectOrUnSelectAll(Integer userId, Integer productId, Integer checked);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}

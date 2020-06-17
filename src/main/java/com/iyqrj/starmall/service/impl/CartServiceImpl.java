package com.iyqrj.starmall.service.impl;

import com.iyqrj.starmall.entity.Cart;
import com.iyqrj.starmall.mapper.CartMapper;
import com.iyqrj.starmall.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lrj
 * @since 2020-06-17
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

}

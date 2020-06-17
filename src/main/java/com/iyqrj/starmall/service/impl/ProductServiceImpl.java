package com.iyqrj.starmall.service.impl;

import com.iyqrj.starmall.entity.Product;
import com.iyqrj.starmall.mapper.ProductMapper;
import com.iyqrj.starmall.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author lrj
 * @since 2020-06-17
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}

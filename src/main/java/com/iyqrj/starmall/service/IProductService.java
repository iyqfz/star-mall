package com.iyqrj.starmall.service;

import com.github.pagehelper.PageInfo;
import com.iyqrj.starmall.common.ServerResponse;
import com.iyqrj.starmall.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iyqrj.starmall.vo.ProductDetailVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lrj
 * @since 2020-10-19
 */
public interface IProductService extends IService<Product> {

    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse<String> setSaleStatus(Integer productId, Integer status) ;

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);

    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);
}

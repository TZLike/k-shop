package com.huatech.shop.module.product.service;


import com.github.pagehelper.PageInfo;
import com.huatech.shop.common.base.IBaseService;
import com.huatech.shop.common.dto.ProductDto;
import com.huatech.shop.module.product.entity.Product;
import com.huatech.shop.module.product.param.ProductParam;

import java.util.List;

/**
 * @Author leek
 * @Date 2019-08-22 下午3:03
 * @Version 1.0
 * @Description
 */
public interface IProductService extends IBaseService<Product, String> {
    void saveOrUpdate(ProductParam param);

    PageInfo<ProductDto> findCategoryByStatus(ProductParam param, int pageNumber, int pageSize);
    void updateProductStatus(String productId, Integer status);

    List<Product> findProductByCategoryNo(String categoryNo);
}

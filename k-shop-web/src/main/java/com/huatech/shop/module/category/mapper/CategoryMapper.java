package com.huatech.shop.module.category.mapper;


import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.common.dto.CategoryDto;
import com.huatech.shop.module.category.entity.Category;
import com.huatech.shop.module.category.param.CategoryParam;

import java.util.List;

public interface CategoryMapper extends IBaseMapper<Category, Integer> {

    //查询所有的商品类目
    List<CategoryDto> findProductCategory(CategoryParam categoryParam);

    //查看所有已经上线的商品种类
    List<CategoryDto> findCategoryList();
}
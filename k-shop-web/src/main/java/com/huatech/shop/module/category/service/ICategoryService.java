package com.huatech.shop.module.category.service;

import com.github.pagehelper.PageInfo;
import com.huatech.shop.common.base.IBaseService;
import com.huatech.shop.common.dto.CategoryDto;
import com.huatech.shop.module.category.entity.Category;
import com.huatech.shop.module.category.param.CategoryParam;

import java.util.List;

/**
 * @ClassName ICategoryService
 * @Description TODO
 * @Author like
 * @Date 2019-08-21 11:07
 * @Version 1.0
 **/
public interface ICategoryService extends IBaseService<Category, Integer> {
    //获取所有的商品类目列表
    PageInfo<CategoryDto> findProductCategory(CategoryParam categoryParam);

    void saveOrUpdate(Category category);

    void upOrDownCategory(Integer id, String s);

    List<CategoryDto> findCategoryList();
}

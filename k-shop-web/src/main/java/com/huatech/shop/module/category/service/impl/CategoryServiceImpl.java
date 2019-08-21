package com.huatech.shop.module.category.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.common.base.impl.BaseServiceImpl;
import com.huatech.shop.common.dto.CategoryDto;
import com.huatech.shop.module.category.entity.Category;
import com.huatech.shop.module.category.mapper.CategoryMapper;
import com.huatech.shop.module.category.param.CategoryParam;
import com.huatech.shop.module.category.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Author like
 * @Date 2019-08-21 11:07
 * @Version 1.0
 **/
@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, Integer> implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public IBaseMapper<Category, Integer> getBaseMapper() {
        return categoryMapper;
    }

    @Override
    public PageInfo<CategoryDto> findProductCategory(CategoryParam categoryParam) {

        PageHelper.startPage(categoryParam.getPageNumber(), categoryParam.getPageSize());
        List<CategoryDto> categorys = categoryMapper.findProductCategory(categoryParam);

        return null;
    }
}

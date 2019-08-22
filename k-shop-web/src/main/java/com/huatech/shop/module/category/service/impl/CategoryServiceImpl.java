package com.huatech.shop.module.category.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.common.base.impl.BaseServiceImpl;
import com.huatech.shop.common.dto.CategoryDto;
import com.huatech.shop.module.category.entity.Category;
import com.huatech.shop.module.category.mapper.CategoryMapper;
import com.huatech.shop.module.category.param.CategoryParam;
import com.huatech.shop.module.category.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        List<CategoryDto> dtos = categoryMapper.findProductCategory(categoryParam);
        PageInfo<CategoryDto> pageInfo = new PageInfo<>(dtos);
        if (categoryParam.getPageNumber() > pageInfo.getPages()) {
            return new PageInfo<>(Lists.newArrayList());
        }
        return pageInfo;
    }

    @Override
    public void saveOrUpdate(Category category) {

        if (category.getId() != null) {

            Category db_category = categoryMapper.selectByPrimaryKey(category.getId());
            if (db_category != null) {
                db_category.setImgUrl(category.getImgUrl());
                db_category.setName(category.getName());
                db_category.setUpdateTime(new Date());
                db_category.setStatus(category.getStatus());
                categoryMapper.updateByPrimaryKey(db_category);
            }

        } else {
            category.setCreateTime(new Date());
            category.setCategoryNo(Math.abs(UUID.randomUUID().hashCode()));
            categoryMapper.insertSelective(category);
        }
    }

    @Override
    public void upOrDownCategory(Integer id, String s) {

        Category db_category = categoryMapper.selectByPrimaryKey(id);
        if (db_category != null) {
            db_category.setStatus(s);
            categoryMapper.updateByPrimaryKey(db_category);
        }

    }

    @Override
    public List<CategoryDto> findCategoryList() {

        return categoryMapper.findCategoryList();
    }
}

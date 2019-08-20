package com.huatech.shop.common.base.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huatech.shop.common.base.BaseEntity;
import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.common.base.IBaseService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @Author leek
 * @Date 2018-08-27 下午5:10
 * @Version 1.0
 * @Description
 */
@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity, ID extends Serializable> implements IBaseService<T, ID> {

    public abstract IBaseMapper<T, ID> getBaseMapper();


    public T find(ID id) {
        return getBaseMapper().selectByPrimaryKey(id);
    }


    public Integer count() {
        return getBaseMapper().selectCount(null);
    }


    public void save(T entity) {
        getBaseMapper().insert(entity);
    }


    public void update(T entity) {
        getBaseMapper().updateByPrimaryKey(entity);
    }


    public void delete(ID id) {
        getBaseMapper().deleteByPrimaryKey(id);
    }


    public void delete(T entity) {
        getBaseMapper().delete(entity);
    }


    public List<T> findAll() {
        return getBaseMapper().selectAll();
    }

    public PageInfo<T> findList(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<T> list = getBaseMapper().selectAll();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        return pageInfo;
    }
}

package com.huatech.shop.common.base;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName IBaseService
 * @Description TODO
 * @Author like
 * @Date 2019-08-16 10:03
 * @Version 1.0
 **/

public interface IBaseService<T, ID extends Serializable> {

    T find(ID var1);

    List<T> findAll();

    PageInfo<T> findList(Integer page, Integer size);

    Integer count();

    void save(T var1);

    void update(T var1);

    void delete(ID var1);

    void delete(T var1);

}

package com.huatech.shop.module.dict.service;

import com.github.pagehelper.PageInfo;
import com.huatech.shop.common.base.IBaseService;
import com.huatech.shop.module.dict.entity.DictType;
import com.huatech.shop.module.dict.param.DictParam;

/**
 * @ClassName IDictTypeService
 * @Description TODO
 * @Author like
 * @Date 2019-08-17 23:47
 * @Version 1.0
 **/
public interface IDictTypeService extends IBaseService<DictType, Integer> {
    //获取字典类型列表
    PageInfo<DictType> findListByParam(DictParam dictParam);

    //保存或者删除字典类型
    void saveOrUpdate(DictType dictType);

    //删除字典项
    void deleteById(Integer id);
}

package com.huatech.shop.module.dict.mapper;

import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.module.dict.entity.DictType;
import com.huatech.shop.module.dict.param.DictParam;

import java.util.List;

/**
 * @ClassName DictTypeMapper
 * @Description TODO
 * @Author like
 * @Date 2019-08-17 23:46
 * @Version 1.0
 **/
public interface DictTypeMapper extends IBaseMapper<DictType, Integer> {

    List<DictType> findListByParam(DictParam dictParam);

    //根据名称查询字典是否存在
    List<DictType> findDictTypeByName(String name);

    List<DictType> findDictTypeByCode(String code);
}

package com.huatech.shop.common.base;


import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;

/**
 * @Author leek
 * @Date 2018-08-27 下午5:22
 * @Version 1.0
 * @Description
 */
public interface IBaseMapper<T extends BaseEntity, ID extends Serializable> extends Mapper<T> {


}
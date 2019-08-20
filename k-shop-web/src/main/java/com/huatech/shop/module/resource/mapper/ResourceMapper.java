package com.huatech.shop.module.resource.mapper;


import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.module.resource.entity.Resource;

import java.util.List;

public interface ResourceMapper extends IBaseMapper<Resource, Integer> {

    List<Resource> findResourcesByRoleId(Integer roleId);

    //删除角色和资源对应关系
    void deleteRoleAndResourceById(Integer resourceId);

    List<Resource> selectByParentId(Integer parentId);
}
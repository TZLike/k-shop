package com.huatech.shop.module.resource.service;


import com.huatech.shop.common.base.IBaseService;
import com.huatech.shop.module.resource.entity.Resource;
import com.huatech.shop.module.resource.param.ZTreeView;

import java.util.List;

/**
 * @Author leek
 * @Date 2018-07-18 下午3:53
 * @Version 1.0
 * @Description
 */
public interface IResourceService extends IBaseService<Resource, Integer> {
    void saveOrUpdate(Resource resource);

    /**
     * 获取角色的权限树
     *
     * @param
     * @return
     */
    List<ZTreeView> tree(Integer resourceId);


    //根绝当前角色id查询查询资源
    List<Resource> findResourcesByRoleId(Integer roleId);

    //根据主键删除资源
    void deleteResourceById(Integer resourceId);
}

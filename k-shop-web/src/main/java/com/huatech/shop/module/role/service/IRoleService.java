package com.huatech.shop.module.role.service;


import com.huatech.shop.common.base.IBaseService;
import com.huatech.shop.module.role.entity.Role;

/**
 * @Author leek
 * @Date 2018-07-17 上午10:43
 * @Version 1.0
 * @Description
 */
public interface IRoleService extends IBaseService<Role,Integer> {

    Role findRoleById(Integer roleId);
    void saveOrUpdate(Role role);

    //给角色分配资源
    void grant(Integer id, String[] resourceIds);
}

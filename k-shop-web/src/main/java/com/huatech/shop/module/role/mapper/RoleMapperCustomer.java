package com.huatech.shop.module.role.mapper;


import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.module.role.entity.Role;

import java.util.List;

/**
 * @Author leek
 * @Date 2019-07-17 上午10:42
 * @Version 1.0
 * @Description
 */
public interface RoleMapperCustomer extends IBaseMapper<Role, Integer> {

    Role findRoleById(Integer roleId);

    List<Role> findRoleByName(String name);

}

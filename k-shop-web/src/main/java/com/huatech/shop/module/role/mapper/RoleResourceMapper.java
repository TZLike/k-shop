package com.huatech.shop.module.role.mapper;


import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.module.role.entity.RoleResourceKey;
import org.apache.ibatis.annotations.Delete;

public interface RoleResourceMapper extends IBaseMapper<RoleResourceKey,Integer> {

    //使用mybatis中的注解
    @Delete("delete from t_role_resource where role_id = #{roleId}")
    void deleteByRoleId(Integer roleId);
}
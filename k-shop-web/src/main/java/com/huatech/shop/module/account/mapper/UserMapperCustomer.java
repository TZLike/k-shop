package com.huatech.shop.module.account.mapper;



import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.module.account.entity.User;
import com.huatech.shop.module.account.param.UserParam;
import com.huatech.shop.module.role.entity.Role;

import java.util.List;

/**
 * @Author leek
 * @Date 2018-07-17 上午9:22
 * @Version 1.0
 * @Description
 */
public interface UserMapperCustomer extends IBaseMapper<User,Integer> {

    User findByUserName(String userName);
    List<User> findUserList(UserParam userParam);
    //根据用户的userId来查询用户角色
    List<Role> findRolesByUserId(Integer userId);

}

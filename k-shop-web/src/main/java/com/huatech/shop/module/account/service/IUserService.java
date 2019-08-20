package com.huatech.shop.module.account.service;


import com.github.pagehelper.PageInfo;
import com.huatech.shop.common.base.IBaseService;
import com.huatech.shop.module.account.entity.User;
import com.huatech.shop.module.account.mapper.UserExample;
import com.huatech.shop.module.account.param.UserParam;
import com.huatech.shop.module.role.entity.Role;

import java.util.List;

/**
 * @Author leek
 * @Date 2019-07-16 下午5:48
 * @Version 1.0
 * @Description
 */
public interface IUserService extends IBaseService<User, Integer> {

    User findByUserName(String username);  //根据姓名查找用户

    PageInfo<User> findUserList(UserParam userParam); //传输参数

    void saveOrUpdate(User user); //更新或者修改用户

    /**
     * 给用户分配角色
     *
     * @param id      用户ID
     * @param roleIds 角色Ids
     */
    void grant(Integer id, String[] roleIds);

//    //根据条件查询用户
    User findByExample(UserExample userExample);

    //根据用户id来查询用户角色信息
    List<Role> findRolesByUserId(Integer userId);

}

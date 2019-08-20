package com.huatech.shop.module.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.huatech.shop.base.constants.ShopConstants;
import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.common.base.impl.BaseServiceImpl;
import com.huatech.shop.common.exception.ExceptionCustomer;
import com.huatech.shop.common.utils.MD5Utils;
import com.huatech.shop.module.account.entity.User;
import com.huatech.shop.module.account.entity.UserRoleKey;
import com.huatech.shop.module.account.mapper.UserExample;
import com.huatech.shop.module.account.mapper.UserMapperCustomer;
import com.huatech.shop.module.account.mapper.UserRoleMapper;
import com.huatech.shop.module.account.param.UserParam;
import com.huatech.shop.module.account.service.IUserService;
import com.huatech.shop.module.role.entity.Role;
import com.huatech.shop.module.role.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * @Author leek
 * @Date 2019-07-17 上午9:17
 * @Version 1.0
 * @Description
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements IUserService {

    @Autowired
    private UserMapperCustomer userMapperCustomer;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public User findByUserName(String username) {
        return userMapperCustomer.findByUserName(username);
    }

    @Override
    public PageInfo<User> findUserList(UserParam param) {
        PageHelper.startPage(param.getPageNumber(), param.getPageSize());
        PageInfo<User> pageInfo = new PageInfo<>(userMapperCustomer.findUserList(param));
        if (param.getPageNumber() > pageInfo.getPages()) {
            return new PageInfo<>(Lists.newArrayList());
        }
        return pageInfo;
    }

    //新增或者修改用户
    @Override
    public void saveOrUpdate(User user) {

        if (user.getUid() != null) {
            User dbUser = find(user.getUid());
            dbUser.setNickName(user.getNickName());
            dbUser.setSex(user.getSex());
            dbUser.setTelephone(user.getTelephone());
            dbUser.setEmail(user.getEmail());
            dbUser.setLocked(user.getLocked());
            dbUser.setDescription(user.getDescription());
            dbUser.setUpdateTime(new Date());
            userMapperCustomer.updateByPrimaryKey(dbUser);
        } else {
            //新增用户
            //根据用户名来查询是否有重复的名称
            User db_user = findByUserName(user.getUserName());
            if (db_user != null) {
                throw new ExceptionCustomer(ShopConstants.FAIL, ShopConstants.User.USER_NAME_EXIST);
            }
            user.setCreateTime(new Date());
            user.setBirthday(new Date());
            user.setPassword(MD5Utils.md5(ShopConstants.User.PASSWORD_ORIGINAL));
            //后期要做用户邮箱激活
            //保存用户
            userMapperCustomer.insertSelective(user);
        }
    }

    @Override
    public void grant(Integer id, String[] roleIds) {
        User user = find(id);
        Assert.notNull(user, "用户不存在");
        if (roleIds != null) {
            for (int i = 0; i < roleIds.length; i++) {
                Integer rid = Integer.parseInt(roleIds[i]);
                UserRoleKey userRoleKey = new UserRoleKey();
                userRoleKey.setUserId(id);
                userRoleKey.setRoleId(rid);
                userRoleMapper.insertSelective(userRoleKey);
            }
        }
    }

    @Override
    public User findByExample(UserExample userExample) {
        return userMapperCustomer.selectOneByExample(userExample);
    }

    @Override
    public List<Role> findRolesByUserId(Integer userId) {
        return userMapperCustomer.findRolesByUserId(userId);
    }

    @Override
    public IBaseMapper<User, Integer> getBaseMapper() {
        return this.userMapperCustomer;
    }

}

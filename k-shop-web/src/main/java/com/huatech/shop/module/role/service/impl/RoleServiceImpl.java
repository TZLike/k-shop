package com.huatech.shop.module.role.service.impl;

import com.huatech.shop.base.constants.ErrorCode;
import com.huatech.shop.base.constants.ShopConstants;
import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.common.base.impl.BaseServiceImpl;
import com.huatech.shop.common.exception.ExceptionCustomer;
import com.huatech.shop.module.role.entity.Role;
import com.huatech.shop.module.role.entity.RoleResourceKey;
import com.huatech.shop.module.role.mapper.RoleMapperCustomer;
import com.huatech.shop.module.role.mapper.RoleResourceMapper;
import com.huatech.shop.module.role.service.IRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * @Author leek
 * @Date 2018-07-17 上午10:43
 * @Version 1.0
 * @Description
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements IRoleService {

    @Autowired
    private RoleMapperCustomer roleMapperCustomer;

    @Autowired
    private RoleResourceMapper roleResourceMapper;


    @Override
    public Role findRoleById(Integer roleId) {
        return roleMapperCustomer.findRoleById(roleId);
    }

    //新增和修改角色
    @Override
    public void saveOrUpdate(Role role) {
        if (role.getRid() != null) {
            Role dbRole = find(role.getRid());
            dbRole.setUpdateTime(new Date());
            dbRole.setName(role.getName());
            dbRole.setDescription(role.getDescription());
            dbRole.setUpdateTime(new Date());
            dbRole.setStatus(role.getStatus());
            roleMapperCustomer.updateByPrimaryKey(dbRole);
        } else {
            //查询添加的角色系统是否已经存在
            List<Role> roles = roleMapperCustomer.findRoleByName(role.getName());
            if (roles != null && roles.size() > 0) {
                throw new ExceptionCustomer(ErrorCode.ROLE_NAME_EXIST.getCode(), ErrorCode.ROLE_NAME_EXIST.getMessage());
            }
            role.setCreateTime(new Date());
            role.setUpdateTime(new Date());
            roleMapperCustomer.insert(role);
        }
    }

    @Override
    public void grant(Integer id, String[] resourceIds) {
        Role role = find(id);
        Assert.notNull(role, "角色不存在");
        if (resourceIds != null) {
            //先删除中间表中的当前角色
            roleResourceMapper.deleteByRoleId(id);
            for (int i = 0; i < resourceIds.length; i++) {
                if (StringUtils.isBlank(resourceIds[i]) || "0".equals(resourceIds[i])) {
                    continue;
                }
                Integer rid = Integer.parseInt(resourceIds[i]);
                RoleResourceKey roleResourceKey = new RoleResourceKey(id, rid);
                roleResourceMapper.insertSelective(roleResourceKey);

            }
        }


    }

    @Override
    public IBaseMapper<Role, Integer> getBaseMapper() {
        return roleMapperCustomer;
    }

}

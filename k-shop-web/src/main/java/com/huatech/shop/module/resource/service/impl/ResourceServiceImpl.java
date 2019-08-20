package com.huatech.shop.module.resource.service.impl;

import com.huatech.shop.base.constants.ErrorCode;
import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.common.base.impl.BaseServiceImpl;
import com.huatech.shop.common.exception.ExceptionCustomer;
import com.huatech.shop.module.resource.entity.Resource;
import com.huatech.shop.module.resource.mapper.ResourceMapper;
import com.huatech.shop.module.resource.param.ZTreeView;
import com.huatech.shop.module.resource.service.IResourceService;
import com.huatech.shop.module.role.entity.Role;
import com.huatech.shop.module.role.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author leek
 * @Date 2019-07-18 下午3:53
 * @Version 1.0
 * @Description
 */
@Service
@Transactional
public class ResourceServiceImpl extends BaseServiceImpl<Resource, Integer> implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private IRoleService roleService;

    @Override
    public IBaseMapper<Resource, Integer> getBaseMapper() {
        return resourceMapper;
    }

    @Override
    public void saveOrUpdate(Resource resource) {
        if (resource.getId() != null) {
            Resource dbResource = find(resource.getId());
            dbResource.setUpdateTime(new Date());
            dbResource.setName(resource.getName());
            dbResource.setSourceKey(resource.getSourceKey());
            dbResource.setType(resource.getType());
            dbResource.setSourceUrl(resource.getSourceUrl());
            dbResource.setLevel(resource.getLevel());
            dbResource.setSort(resource.getSort());
            dbResource.setIsHide(resource.getIsHide());
            dbResource.setDescription(resource.getDescription());
            dbResource.setUpdateTime(new Date());
            dbResource.setParentId(resource.getParentId());
            update(dbResource);
        } else {
            resource.setCreateTime(new Date());
            resource.setUpdateTime(new Date());
            save(resource);
        }
    }

    @Override
    public List<ZTreeView> tree(Integer resourceId) {
        List<ZTreeView> resulTreeNodes = new ArrayList<ZTreeView>();
        Role role = roleService.find(resourceId);
        //换成mybatis这种取值不对
//        List<Resource> roleResources = role.getResources();
        List<Resource> roleResources = resourceMapper.findResourcesByRoleId(role.getRid());
        resulTreeNodes.add(new ZTreeView(0L, null, "系统菜单", true));
        ZTreeView node;
        List<Resource> all = resourceMapper.selectAll();
        for (Resource resource : all) {
            node = new ZTreeView();
            node.setId(Long.valueOf(resource.getId()));
            if (resource.getParentId() == null) {
                node.setPid(0L);
            } else {
                node.setPid(Long.valueOf(resource.getParentId()));
            }
            node.setName(resource.getName());
            if (roleResources != null && roleResources.contains(resource)) {
                node.setChecked(true);
            }
            resulTreeNodes.add(node);
        }
        return resulTreeNodes;
    }

    //根据当前角色id查询角色下所有的资源
    @Override
    public List<Resource> findResourcesByRoleId(Integer roleId) {
        return resourceMapper.findResourcesByRoleId(roleId);
    }

    @Override
    public void deleteResourceById(Integer resourceId) {
//查询所删除测资源是否存在
        Resource resource = resourceMapper.selectByPrimaryKey(resourceId);
        if (resource == null) {
            throw new ExceptionCustomer(ErrorCode.RESOURCE_NOT_EXIST.getCode(), ErrorCode.RESOURCE_NOT_EXIST.getMessage());
        }
       //查询所删资源是否有子集
        List<Resource> childrenResources = resourceMapper.selectByParentId(resourceId);
        if(childrenResources !=null && childrenResources.size() >0){
            throw new ExceptionCustomer(ErrorCode.RESOURCE_HAVE_CHILD.getCode(), ErrorCode.RESOURCE_HAVE_CHILD.getMessage());
        }
        //在删除资源之前，先删掉t_role_resource表重的关联
        resourceMapper.deleteRoleAndResourceById(resourceId);
        resourceMapper.deleteByPrimaryKey(resourceId);
    }
}

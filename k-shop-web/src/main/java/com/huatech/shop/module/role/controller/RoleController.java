package com.huatech.shop.module.role.controller;

import com.github.pagehelper.PageInfo;
import com.huatech.shop.base.constants.ShopConstants;
import com.huatech.shop.base.controller.BaseController;
import com.huatech.shop.base.init.SysParamService;
import com.huatech.shop.common.result.ResponseResult;
import com.huatech.shop.module.dict.entity.DictInfo;
import com.huatech.shop.module.role.entity.Role;
import com.huatech.shop.module.role.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private SysParamService sysParamService;

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "admin/role/index";
    }

    @RequestMapping(value = {"/list"})
    @ResponseBody
    public PageInfo<Role> list(int pageNumber, int pageSize) {

        PageInfo<Role> pageInfo = roleService.findList(pageNumber, pageSize);
        return pageInfo;
    }

    //    前往角色添加页面
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap modelMap) {
        List<DictInfo> infos = sysParamService.listParam(ShopConstants.Role.ROLE_STATUS);
        modelMap.put("roles", infos);
        return "admin/role/form";
    }

    //编辑角色
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        Role role = roleService.find(id);
        map.put("role", role);
        List<DictInfo> infos = sysParamService.listParam(ShopConstants.Role.ROLE_STATUS);
        map.put("roles", infos);
        return "admin/role/form";
    }


    //添加角色
    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult edit(Role role) {
        roleService.saveOrUpdate(role);
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@PathVariable Integer id) {
        roleService.delete(id);
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }

    @RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
    public String grant(@PathVariable Integer id, ModelMap map) {
        Role role = roleService.find(id);
        map.put("role", role);
        return "admin/role/grant";
    }

    @RequestMapping(value = "/grant/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult grant(@PathVariable Integer id,
                                @RequestParam(required = false) String[] resourceIds, ModelMap map) {
        roleService.grant(id, resourceIds);
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }
}

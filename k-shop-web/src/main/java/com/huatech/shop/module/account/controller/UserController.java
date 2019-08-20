package com.huatech.shop.module.account.controller;

import com.github.pagehelper.PageInfo;
import com.huatech.shop.base.constants.ShopConstants;
import com.huatech.shop.base.controller.BaseController;
import com.huatech.shop.base.init.SysParamService;
import com.huatech.shop.base.result.DataGridResultInfo;
import com.huatech.shop.common.constants.ApiConstants;
import com.huatech.shop.common.result.ResponseResult;
import com.huatech.shop.module.account.entity.User;
import com.huatech.shop.module.account.mapper.UserExample;
import com.huatech.shop.module.account.param.UserParam;
import com.huatech.shop.module.account.service.IUserService;
import com.huatech.shop.module.dict.entity.DictInfo;
import com.huatech.shop.module.role.entity.Role;
import com.huatech.shop.module.role.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author leek
 * @Date 2019-07-17 上午11:41
 * @Version 1.0  用户处理器类
 * @Description
 */
@Controller
@RequestMapping("/admin/user")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private SysParamService sysParamService;

    /**
     * 用户管理初始化页面
     *
     * @return
     */
    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "admin/user/index";
    }

    /**
     * 查询集合
     *
     * @return
     */
    @RequestMapping(value = {"/list"})
    @ResponseBody
    public DataGridResultInfo list(UserParam userParam) {
        DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
        //查询列表总数
        PageInfo<User> pageInfo = userService.findUserList(userParam);
        dataGridResultInfo.setRows(pageInfo.getList());
        dataGridResultInfo.setTotal(pageInfo.getSize());
        return dataGridResultInfo;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap modelMap) {
        findInfosByTypeCode(modelMap);
        return "admin/user/form";
    }

    public void findInfosByTypeCode(ModelMap modelMap) {
        List<DictInfo> infos = sysParamService.listParam(ShopConstants.User.USER_SEX);
        modelMap.put("infos", infos);
        List<DictInfo> userInfos = sysParamService.listParam(ShopConstants.User.USER_STATUS);
        modelMap.put("userInfos", userInfos);

    }

    //查找用户信息前往编辑页面
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        User user = userService.find(id);
        map.put("user", user);
        findInfosByTypeCode(map);
        return "admin/user/form";
    }

    /**
     * 编辑用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult edit(User user) {

        userService.saveOrUpdate(user);
        return new ResponseResult().ok(ApiConstants.SUCCESS, "success");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@PathVariable Integer id) {
        userService.delete(id);
        return new ResponseResult().ok(ApiConstants.SUCCESS, "success");
    }

    @RequestMapping(value = "/grant/{userName}", method = RequestMethod.GET)
    public String grant(@PathVariable String userName, ModelMap map) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(userName);
        User user = userService.findByExample(userExample);
        map.put("user", user);
        List<Role> set = userService.findRolesByUserId(user.getUid());
        List<Integer> roleIds = new ArrayList<Integer>();
        if (set != null && set.size() > 0) {
            for (Role role : set) {
                roleIds.add(role.getRid());

            }
        }
        map.put("roleIds", roleIds);
        List<Role> roles = roleService.findAll();
        map.put("roles", roles);
        return "admin/user/grant";
    }

    /**
     * 给用户授予角色
     *
     * @param id
     * @param roleIds
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/grant/{id}", method = RequestMethod.POST)
    public ResponseResult grant(@PathVariable Integer id, String[] roleIds, ModelMap map) {
        userService.grant(id, roleIds);
        return new ResponseResult().ok(ApiConstants.SUCCESS, "success");
    }

    @RequestMapping("/password")
    public String password() {
        return "admin/user/password";
    }

    //
    @RequestMapping("/changePassword")
    @ResponseBody
    public ResponseResult changePassword(Integer id, String password, String newPassword) {
//        userService.changePassword(id,password,newPassword);
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ResponseResult().ok(ApiConstants.SUCCESS, "success");
    }
}



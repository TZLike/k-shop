package com.huatech.shop.module.resource.controller;

import com.github.pagehelper.PageInfo;
import com.huatech.shop.base.constants.ShopConstants;
import com.huatech.shop.base.controller.BaseController;
import com.huatech.shop.common.result.ResponseResult;
import com.huatech.shop.module.resource.entity.Resource;
import com.huatech.shop.module.resource.param.ZTreeView;
import com.huatech.shop.module.resource.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author leek
 * @Date 2019-07-18 下午3:52
 * @Version 1.0
 * @Description
 */
@Controller
@RequestMapping("/admin/resource")
public class ResourceController extends BaseController {

    @Autowired
    private IResourceService resourceService;

    @RequestMapping("/tree/{resourceId}")
    @ResponseBody
    public List<ZTreeView> tree(@PathVariable Integer resourceId) {
        List<ZTreeView> list = resourceService.tree(resourceId);
        return list;
    }

    @RequestMapping("/index")
    public String index() {
        return "admin/resource/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Resource> list(int pageNumber, int pageSize) {
        PageInfo<Resource> pageInfo = resourceService.findList(pageNumber, pageSize);
        return pageInfo;

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        List<Resource> list = resourceService.findAll();
        map.put("list", list);
        return "admin/resource/form";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        Resource resource = resourceService.find(id);
        map.put("resource", resource);

        List<Resource> list = resourceService.findAll();
        map.put("list", list);
        return "admin/resource/form";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult edit(Resource resource) {
        resourceService.saveOrUpdate(resource);
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@PathVariable Integer id) {
        resourceService.deleteResourceById(id);
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }
}

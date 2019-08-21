package com.huatech.shop.module.category.controller;

import com.github.pagehelper.PageInfo;
import com.huatech.shop.base.constants.ShopConstants;
import com.huatech.shop.base.controller.BaseController;
import com.huatech.shop.base.init.SysParamService;
import com.huatech.shop.common.dto.CategoryDto;
import com.huatech.shop.common.result.ResponseResult;
import com.huatech.shop.module.banner.entity.Banner;
import com.huatech.shop.module.category.entity.Category;
import com.huatech.shop.module.category.param.CategoryParam;
import com.huatech.shop.module.category.service.ICategoryService;
import com.huatech.shop.module.dict.entity.DictInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName CategoryController
 * @Description TODO
 * @Author like  商品类目管理
 * @Date 2019-08-21 11:05
 * @Version 1.0
 **/
@RequestMapping("/admin/category")
@Slf4j
@Controller
public class CategoryController extends BaseController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private SysParamService sysParamService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        log.info("======index=======");
        findInfosByTypeCode(modelMap);
        return "/admin/category/index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<CategoryDto> list(CategoryParam categoryParam) {
        log.info("====category list======");
        PageInfo<CategoryDto> category = categoryService.findProductCategory(categoryParam);
        return category;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap modelMap) {
        findInfosByTypeCode(modelMap);
        return "admin/category/form";
    }

    public void findInfosByTypeCode(ModelMap modelMap) {
        List<DictInfo> infos = sysParamService.listParam(ShopConstants.Category.CATEGORY_STATUS);
        modelMap.put("infos", infos);


    }

    /**
     * 添加商品分类类目
     *
     * @param category
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResponseResult addCategory(Category category) {
        categoryService.saveOrUpdate(category);
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }

    /**
     * 上线商品分类
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/up/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult upCategory(@PathVariable("id") Integer id) {
        categoryService.upOrDownCategory(id, "1");
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }

    /**
     * 下线商品分类图
     *
     * @param id
     * @return
     */

    @RequestMapping(value = "/down/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult downCategory(@PathVariable("id") Integer id) {
        categoryService.upOrDownCategory(id, "2");
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }


    /**
     * 删除商品分类
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult deleteCategory(@PathVariable("id") Integer id) {
        categoryService.delete(id);
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }

}

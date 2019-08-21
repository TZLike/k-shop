package com.huatech.shop.module.category.controller;

import com.github.pagehelper.PageInfo;
import com.huatech.shop.base.controller.BaseController;
import com.huatech.shop.common.dto.CategoryDto;
import com.huatech.shop.module.category.param.CategoryParam;
import com.huatech.shop.module.category.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        log.info("======index=======");
        return "/admin/category/index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<CategoryDto> list(CategoryParam categoryParam) {
        log.info("====category list======");
        PageInfo<CategoryDto> category = categoryService.findProductCategory(categoryParam);
        return category;
    }

}

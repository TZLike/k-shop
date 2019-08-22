package com.huatech.shop.module.product.controller;

import com.github.pagehelper.PageInfo;
import com.huatech.shop.base.constants.ShopConstants;
import com.huatech.shop.base.init.SysParamService;
import com.huatech.shop.common.dto.CategoryDto;
import com.huatech.shop.common.dto.ProductDto;
import com.huatech.shop.common.result.ResponseResult;
import com.huatech.shop.module.category.service.ICategoryService;
import com.huatech.shop.module.dict.entity.DictInfo;
import com.huatech.shop.module.product.entity.Product;
import com.huatech.shop.module.product.param.ProductParam;
import com.huatech.shop.module.product.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author leek
 * @Date 2019-08-22 下午3:03
 * @Version 1.0
 * @Description 商品信息表
 */
@Controller
@RequestMapping("/admin/product")
@Slf4j
public class ProductController {


    @Autowired
    private IProductService productService;

    @Autowired
    private SysParamService sysParamService;

    @Autowired
    private ICategoryService categoryService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        infos(modelMap);
        return "/admin/product/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String productAdd(ModelMap modelMap) {
        infos(modelMap);
        return "/admin/product/form";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<ProductDto> bannerList(ProductParam param) {

        PageInfo<ProductDto> productDtoPageInfo = productService.findCategoryByStatus(param, param.getPageNumber(), param.getPageSize());
        return productDtoPageInfo;
    }


    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult addProduct(ProductParam param, HttpSession session) {
        log.info("param==" + param);
        param.setProductCreateUser((String) session.getAttribute("username"));
        productService.saveOrUpdate(param);
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }


    @RequestMapping(value = {"/up/{productId}/{status}", "/down/{productId}/{status}"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult up(@PathVariable("productId") String productId, @PathVariable("status") Integer status) {
        productService.updateProductStatus(productId, status);
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");

    }


    @RequestMapping(value = "/detail/{productId}")
    public String detail(@PathVariable("productId") String productId, ModelMap modelMap) {
        Product product = productService.find(productId);
        modelMap.put("product", product);
        infos(modelMap);
        return "/admin/product/edit";
    }


    public void infos(ModelMap modelMap) {
        //商品上架状态
        List<DictInfo> productStatus = sysParamService.listParam(ShopConstants.Product.PRODUCT_UP_AND_DOWN);
        modelMap.put("productStatus", productStatus);
        //商品销售平台
        List<DictInfo> productSales = sysParamService.listParam(ShopConstants.Product.PRODUCT_SALE_PLAT);
        modelMap.put("productSales", productSales);
        //是否为推荐商品
        List<DictInfo> productIfRecommends = sysParamService.listParam(ShopConstants.Product.PRODUCT_IS_COMMEND);
        modelMap.put("productIfRecommends", productIfRecommends);
        //商品可否开发票
        List<DictInfo> productIfCanInvoices = sysParamService.listParam(ShopConstants.Product.PRODUCT_IS_CAN_TICKET);
        modelMap.put("productIfCanInvoices", productIfCanInvoices);
        //是否为实物商品
        List<DictInfo> productGoodsTypes = sysParamService.listParam(ShopConstants.Product.PRODUCT_TYPE);
        modelMap.put("productGoodsTypes", productGoodsTypes);
        List<CategoryDto> categoryList = categoryService.findCategoryList();
        modelMap.put("categoryList", categoryList);

    }

    @RequestMapping(value = "/del/{productId}")
    @ResponseBody
    public ResponseResult del(@PathVariable("productId") String productId) {
        //直接从数据库删除,不再进行判断有无此商品
        productService.delete(productId);
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }
}

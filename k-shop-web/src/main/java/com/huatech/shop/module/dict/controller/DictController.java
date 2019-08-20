package com.huatech.shop.module.dict.controller;

import com.github.pagehelper.PageInfo;
import com.huatech.shop.base.constants.ShopConstants;
import com.huatech.shop.base.controller.BaseController;
import com.huatech.shop.common.constants.ApiConstants;
import com.huatech.shop.common.result.ResponseResult;
import com.huatech.shop.module.dict.dto.DictInfoDTO;
import com.huatech.shop.module.dict.entity.DictInfo;
import com.huatech.shop.module.dict.entity.DictType;
import com.huatech.shop.module.dict.param.DictParam;
import com.huatech.shop.module.dict.service.IDictInfoService;
import com.huatech.shop.module.dict.service.IDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DictController
 * @Description TODO  数据字典相关
 * @Author like
 * @Date 2019-08-17 23:42
 * @Version 1.0
 **/
@RequestMapping(value = "/admin/dict")
@Slf4j
@Controller
public class DictController extends BaseController {

    @Autowired
    private IDictTypeService typeService;

    @Autowired
    private IDictInfoService infoService;

    @GetMapping(value = "/index")
    public String index() {
        log.info("=======dict index======");
        return "admin/dict/index";
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public PageInfo<DictType> list(DictParam dictParam) {
        PageInfo<DictType> pageInfo = typeService.findListByParam(dictParam);
        return pageInfo;
    }

    //添加字典类型
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("add", "add");
        log.info("=====dict add=====");
        return "admin/dict/form";

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model) {
        DictType type = typeService.find(id);
        model.addAttribute("edit", "edit");
        model.addAttribute("dictType", type);
        log.info("=====dict edit=====");
        return "admin/dict/form";

    }

    //添加或编辑字典类型
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult saveOrUpdate(DictType dictType) {
        log.info("====dictType====");
        typeService.saveOrUpdate(dictType);
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");

    }

    //前往字典列表页面
    @RequestMapping("/dictInfo")
    public String dictInfo(Integer id, ModelMap modelMap) {
        DictType dictType = typeService.find(id);
        modelMap.put("dictType", dictType);
        return "/admin/dict/dictInfo";
    }


    /**
     * 查询集合
     *
     * @return
     */
    @RequestMapping(value = {"/info/list"}, method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<DictInfoDTO> infoList(int pageNumber, int pageSize, String typeCode) {
        PageInfo<DictInfoDTO> page = infoService.findByTypeCode(pageNumber, pageSize, typeCode);
        return page;
    }

    //前往字典添加界面
    @RequestMapping("/info/add")
    public String infoAdd(ModelMap modelMap, String typeCode) {
        modelMap.put("typeCode", typeCode);
        return "admin/dict/infoForm";
    }

    //    //前往编辑页面
    @RequestMapping("/info/edit/{id}")
    public String infoEdit(@PathVariable Integer id, ModelMap modelMap) {
        DictInfo dictInfo = infoService.find(id);
        modelMap.put("dictInfo", dictInfo);
        return "/admin/dict/infoForm";
    }


    //    //删除字典项
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public ResponseResult delete(@PathVariable Integer id) {

        typeService.deleteById(id);
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }

    //删除字典明细项
    @RequestMapping("/info/delete/{id}")
    @ResponseBody
    public ResponseResult deleteInfo(@PathVariable Integer id) {
        infoService.delete(id);
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }

    //    //编辑提交
    @RequestMapping(value = {"/info/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult infoEdit(DictInfo dictInfo) {

        infoService.saveOrUpdate(dictInfo);
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");


    }
}

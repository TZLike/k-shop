package com.huatech.shop.module.banner.controller;

import com.github.pagehelper.PageInfo;
import com.huatech.shop.base.config.FastDFSClientWrapper;
import com.huatech.shop.base.constants.ShopConstants;
import com.huatech.shop.base.controller.BaseController;
import com.huatech.shop.base.init.SysParamService;
import com.huatech.shop.common.dto.BannerDto;
import com.huatech.shop.common.result.ResponseResult;
import com.huatech.shop.module.banner.entity.Banner;
import com.huatech.shop.module.banner.param.BannerParam;
import com.huatech.shop.module.banner.service.IBannerService;
import com.huatech.shop.module.dict.entity.DictInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName BannerController
 * @Description TODO
 * @Author like
 * @Date 2019-08-20 20:16
 * @Version 1.0
 **/
@Slf4j
@RequestMapping(value = "/admin/banner")
@Controller
public class BannerController extends BaseController {


    @Autowired
    private SysParamService sysParamService;

    @Autowired
    private IBannerService bannerService;

    @Autowired
    private FastDFSClientWrapper clientWrapper;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        log.info("====banner index ======");
        findInfosByTypeCode(modelMap);
        return "admin/banner/index";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<BannerDto> list(BannerParam bannerParam) {
        log.info("====banner list======");
        PageInfo<BannerDto> banners = bannerService.findBanners(bannerParam);
        return banners;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap modelMap) {
        findInfosByTypeCode(modelMap);
        return "admin/banner/form";
    }

    public void findInfosByTypeCode(ModelMap modelMap) {
        List<DictInfo> channels = sysParamService.listParam(ShopConstants.Banner.BANNER_CHANNEL);
        modelMap.put("channels", channels);
        List<DictInfo> positions = sysParamService.listParam(ShopConstants.Banner.BANNER_POSITION);
        modelMap.put("positions", positions);
        List<DictInfo> status = sysParamService.listParam(ShopConstants.Banner.BANNER_STATUS);
        modelMap.put("status", status);

    }

    /**
     * 上传图片
     *
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult bannerUpload(MultipartFile multipartFile) {

        String fileUrl = "";
        if (multipartFile != null) {
            try {
                fileUrl = clientWrapper.uploadFile(multipartFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success").addData("url", fileUrl);
    }

    /**
     * 添加banner图
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/addBanner")
    @ResponseBody
    public ResponseResult addBanner(Banner param) {
        bannerService.saveOrUpdate(param);
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }

    /**
     * 上线banner图
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/up/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult upBanner(@PathVariable("id") Integer id) {
        bannerService.upOrDownBanner(id, "1");
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }

    /**
     * 下线banner图
     *
     * @param id
     * @return
     */

    @RequestMapping(value = "/down/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult downBanner(@PathVariable("id") Integer id) {
        bannerService.upOrDownBanner(id, "2");
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult deleteBanner(@PathVariable("id") Integer id) {
        bannerService.delete(id);
        return new ResponseResult().ok(ShopConstants.SUCCESS, "success");
    }

}

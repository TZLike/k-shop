package com.huatech.shop.module.banner.service;

import com.github.pagehelper.PageInfo;
import com.huatech.shop.common.dto.BannerDto;
import com.huatech.shop.module.banner.entity.Banner;
import com.huatech.shop.module.banner.param.BannerParam;

/**
 * @ClassName IBannerService
 * @Description TODO
 * @Author like
 * @Date 2019-08-20 20:25
 * @Version 1.0
 **/
public interface IBannerService {
    //新增或修改banner
    void saveOrUpdate(Banner param);

    PageInfo<BannerDto> findBanners(BannerParam bannerParam);
}

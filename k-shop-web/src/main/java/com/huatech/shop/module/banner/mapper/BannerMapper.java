package com.huatech.shop.module.banner.mapper;

import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.common.dto.BannerDto;
import com.huatech.shop.module.banner.entity.Banner;
import com.huatech.shop.module.banner.param.BannerParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerMapper extends IBaseMapper<Banner,Integer> {


    List<BannerDto> findBanners(BannerParam bannerParam);
}
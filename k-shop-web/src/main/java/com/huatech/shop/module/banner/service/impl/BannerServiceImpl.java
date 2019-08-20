package com.huatech.shop.module.banner.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.huatech.shop.base.constants.ErrorCode;
import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.common.base.impl.BaseServiceImpl;
import com.huatech.shop.common.dto.BannerDto;
import com.huatech.shop.common.exception.ExceptionCustomer;
import com.huatech.shop.module.banner.entity.Banner;
import com.huatech.shop.module.banner.mapper.BannerMapper;
import com.huatech.shop.module.banner.param.BannerParam;
import com.huatech.shop.module.banner.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName BannerServiceImpl
 * @Description TODO
 * @Author like
 * @Date 2019-08-20 20:25
 * @Version 1.0
 **/
@Service
public class BannerServiceImpl extends BaseServiceImpl<Banner, Integer> implements IBannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public IBaseMapper<Banner, Integer> getBaseMapper() {
        return bannerMapper;
    }

    @Override
    public void saveOrUpdate(Banner param) {

        //更新
        if (param.getId() != null) {

            //查询banner是否存在
            Banner banner = bannerMapper.selectByPrimaryKey(param.getId());
            if (banner == null) {
                throw new ExceptionCustomer(ErrorCode.BANNER_NOT_EXISTS.getCode(), ErrorCode.BANNER_NOT_EXISTS.getMessage());
            }
            banner.setChannel(param.getChannel());
            banner.setImgUrl(param.getImgUrl());
            banner.setName(param.getName());
            banner.setPosition(param.getPosition());
            banner.setSubtitle(param.getSubtitle());
            banner.setUrl(param.getUrl());
            bannerMapper.updateByPrimaryKey(banner);
        } else {
            param.setCreateTime(new Date());
            bannerMapper.insertSelective(param);
        }

    }

    @Override
    public PageInfo<BannerDto> findBanners(BannerParam bannerParam) {
        PageHelper.startPage(bannerParam.getPageNumber(), bannerParam.getPageSize());
        PageInfo<BannerDto> pageInfo = new PageInfo<>(bannerMapper.findBanners(bannerParam));
        if (bannerParam.getPageNumber() > pageInfo.getPages()) {
            return new PageInfo<>(Lists.newArrayList());
        }
        return pageInfo;
    }
}

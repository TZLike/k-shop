package com.huatech.shop.module.dict.service;

import com.github.pagehelper.PageInfo;
import com.huatech.shop.common.base.IBaseService;
import com.huatech.shop.module.dict.dto.DictInfoDTO;
import com.huatech.shop.module.dict.entity.DictInfo;

import java.util.List;

/**
 * @ClassName IDictInfoService
 * @Description TODO
 * @Author like
 * @Date 2019-08-17 23:48
 * @Version 1.0
 **/
public interface IDictInfoService extends IBaseService<DictInfo, Integer> {

    PageInfo<DictInfoDTO> findByTypeCode(int pageNumber, int pageSize, String typeCode);

    //保存字典明细数据
    void saveOrUpdate(DictInfo dictInfo);

    //获取所有的字典明细
    List<DictInfo> findListOrderByTypeCode();
}

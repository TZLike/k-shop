package com.huatech.shop.module.dict.mapper;

import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.module.dict.dto.DictInfoDTO;
import com.huatech.shop.module.dict.entity.DictInfo;

import java.util.List;

/**
 * @ClassName DictInfoMapper
 * @Description TODO
 * @Author like
 * @Date 2019-08-17 23:46
 * @Version 1.0
 **/
public interface DictInfoMapper extends IBaseMapper<DictInfo, Integer> {


    List<DictInfoDTO> findDictInfoByDictCode(String dictCode);


    DictInfo findDictInfoByTypeCodeAndDictCode(DictInfo dictInfo);

    void deleteInfoByTypeCode(String typeCode);
}

package com.huatech.shop.module.dict.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huatech.shop.base.constants.ErrorCode;
import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.common.base.impl.BaseServiceImpl;
import com.huatech.shop.common.exception.ExceptionCustomer;
import com.huatech.shop.module.dict.dto.DictInfoDTO;
import com.huatech.shop.module.dict.entity.DictInfo;
import com.huatech.shop.module.dict.mapper.DictInfoMapper;
import com.huatech.shop.module.dict.mapper.DictTypeMapper;
import com.huatech.shop.module.dict.service.IDictInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DictInfoServiceImpl
 * @Description TODO
 * @Author like
 * @Date 2019-08-17 23:51
 * @Version 1.0
 **/
@Service
public class DictInfoServiceImpl extends BaseServiceImpl<DictInfo, Integer> implements IDictInfoService {

    @Autowired
    private DictInfoMapper infoMapper;

    @Autowired
    private DictTypeMapper typeMapper;

    @Override
    public IBaseMapper<DictInfo, Integer> getBaseMapper() {
        return infoMapper;
    }

    @Override
    public PageInfo<DictInfoDTO> findByTypeCode(int pageNumber, int pageSize, String typeCode) {
        PageHelper.startPage(pageNumber, pageSize);
        List<DictInfoDTO> infos = infoMapper.findDictInfoByDictCode(typeCode);
        PageInfo<DictInfoDTO> pageInfos = new PageInfo<>(infos);
        if (pageNumber > pageInfos.getPages()) {
            return new PageInfo<>(new ArrayList<>());
        }
        return pageInfos;


    }


    @Override
    public void saveOrUpdate(DictInfo dictInfo) {

        if (dictInfo.getId() != null) { //更新

            DictInfo info = infoMapper.selectByPrimaryKey(dictInfo.getId());
            if (info == null) {
                throw new ExceptionCustomer(-1, "字典明细不存在");
            }
            if (dictInfo.getInfo().equals(info.getInfo()) && dictInfo.getDictCode().equals(info.getDictCode())) {
                return;
            } else {
                DictInfo db_info = infoMapper.findDictInfoByTypeCodeAndDictCode(dictInfo);
                if (db_info != null) {
                    throw new ExceptionCustomer(ErrorCode.DICT_INFO_IS_EXISTS.getCode(), ErrorCode.DICT_INFO_IS_EXISTS.getMessage());
                } else {
                    //更新
                    infoMapper.updateByPrimaryKey(dictInfo);
                }
            }


        } else {
            //新增
            //通过type_code和dict_code来查询明细是否唯一
            DictInfo db_info = infoMapper.findDictInfoByTypeCodeAndDictCode(dictInfo);
            if (db_info != null) {
                throw new ExceptionCustomer(ErrorCode.DICT_INFO_IS_EXISTS.getCode(), ErrorCode.DICT_INFO_IS_EXISTS.getMessage());
            }
            //插入数据表
            infoMapper.insertSelective(dictInfo);

        }
    }
}

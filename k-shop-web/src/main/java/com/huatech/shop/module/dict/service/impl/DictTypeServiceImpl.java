package com.huatech.shop.module.dict.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.huatech.shop.base.constants.ErrorCode;
import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.common.base.impl.BaseServiceImpl;
import com.huatech.shop.common.exception.ExceptionCustomer;
import com.huatech.shop.module.dict.entity.DictType;
import com.huatech.shop.module.dict.mapper.DictInfoMapper;
import com.huatech.shop.module.dict.mapper.DictTypeMapper;
import com.huatech.shop.module.dict.param.DictParam;
import com.huatech.shop.module.dict.service.IDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName DictTypeServiceImpl
 * @Description TODO
 * @Author like
 * @Date 2019-08-17 23:49
 * @Version 1.0
 **/
@Service
public class DictTypeServiceImpl extends BaseServiceImpl<DictType, Integer> implements IDictTypeService {

    @Autowired
    private DictTypeMapper typeMapper;

    @Autowired
    private DictInfoMapper infoMapper;

    @Override
    public IBaseMapper<DictType, Integer> getBaseMapper() {
        return typeMapper;
    }

    @Override
    public PageInfo<DictType> findListByParam(DictParam dictParam) {

        PageHelper.startPage(dictParam.getPageNumber(), dictParam.getPageSize());
        PageInfo<DictType> pageInfo = new PageInfo<>(typeMapper.findListByParam(dictParam));
        if (dictParam.getPageNumber() > pageInfo.getPages()) {
            return new PageInfo<>(Lists.newArrayList());
        }
        return pageInfo;
    }

    @Override
    public void saveOrUpdate(DictType dictType) {

        //说明是更新
        if (dictType.getId() != null) {
            DictType dbDictType = find(dictType.getId());
            if (dictType == null) {
                throw new ExceptionCustomer(30001, "字典不存在");
            }

            //根据字典名称查询是否重复
            if (!dictType.getTypeName().equals(dbDictType.getTypeName())) {
                DictType dbDictType1 = findDictTypeByName(dictType.getTypeName());
                if (dbDictType1 != null) {
                    throw new ExceptionCustomer(30002, "字典编码已存在");
                }
            }
            dbDictType.setRemark(dictType.getRemark());
            dbDictType.setTypeName(dictType.getTypeName());
            dbDictType.setUpdateTime(new Date());
            update(dbDictType);

        } else {

            //首先查询库中是否已经存在相同类名的字典
            DictType type = findDictTypeByName(dictType.getTypeName());
            if (type != null) {
                throw new ExceptionCustomer(ErrorCode.DICT_IS_EXISTS.getCode(), ErrorCode.DICT_IS_EXISTS.getMessage());
            }
            //根绝字典编码查询字典是否已经存在
            DictType type2 = findDictTypeByCode(dictType.getTypeCode());
            if (type2 != null) {
                throw new ExceptionCustomer(ErrorCode.DICT_IS_EXISTS.getCode(), ErrorCode.DICT_IS_EXISTS.getMessage());
            }
            dictType.setCreateTime(new Date());
            typeMapper.insertSelective(dictType);


        }
    }


    @Override
    public void deleteById(Integer id) {
        DictType type = typeMapper.selectByPrimaryKey(id);
        delete(id);//删除字典项
        //删除字典明细
        infoMapper.deleteInfoByTypeCode(type.getTypeCode());

    }

    public DictType findDictTypeByName(String name) {
        List<DictType> types = typeMapper.findDictTypeByName(name);
        if (types != null && types.size() > 0) {
            return types.get(0);
        }
        return null;
    }

    public DictType findDictTypeByCode(String code) {
        List<DictType> types = typeMapper.findDictTypeByCode(code);
        if (types != null && types.size() > 0) {
            return types.get(0);
        }
        return null;
    }
}

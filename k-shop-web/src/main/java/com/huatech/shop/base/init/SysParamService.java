package com.huatech.shop.base.init;


import com.huatech.shop.module.dict.entity.DictInfo;
import com.huatech.shop.module.dict.service.IDictInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author leek
 * @Date 2018-07-02 下午5:11   程序启动将数据字典加载到内存中
 * @Version 1.0
 * @Description
 */
@Slf4j
public class SysParamService {

    private Map<String, List<DictInfo>> paramMap;
    private Map<String, String> paramNameMap;
    @Autowired
    private IDictInfoService dictInfoService;

    public void initMethod() {
        try {
            paramMap = new ConcurrentHashMap<>();
            paramNameMap = new ConcurrentHashMap<>();
            List<DictInfo> infoList = new ArrayList<>();

            List<DictInfo> dictInfos = dictInfoService.findListOrderByTypeCode();
            for (DictInfo info : dictInfos) {
                if (!paramMap.keySet().contains(info.getTypeCode())) {
                    infoList = new ArrayList<>();
                }
                infoList.add(info);
                paramMap.put(info.getTypeCode(), infoList);
                paramNameMap.put(info.getTypeCode() + info.getDictCode(), info.getInfo());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }

    //获取字典列表
    public List<DictInfo> listParam(String typeCode) {

        List<DictInfo> dictInfos = paramMap.get(typeCode);
        if (dictInfos != null && dictInfos.size() > 0) {
            Collections.sort(dictInfos, new Comparator<DictInfo>() {
                @Override
                public int compare(DictInfo info, DictInfo info2) {
                    return info.getDictCode().compareTo(info2.getDictCode());
                }
            });
        }
        return dictInfos;

    }

    //获取每个参数的具体值
    public String getParamName(String typeCode, String dictCode) {
        String info = paramNameMap.get(typeCode + dictCode);
        if (StringUtils.isBlank(info)) {
            return "";
        } else {
            return info;
        }
    }

}

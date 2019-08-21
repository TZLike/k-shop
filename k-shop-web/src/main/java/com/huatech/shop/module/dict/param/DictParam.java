package com.huatech.shop.module.dict.param;

import com.huatech.shop.common.base.BasePageQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName DictParam
 * @Description TODO
 * @Author like
 * @Date 2019-08-18 00:09
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictParam extends BasePageQuery {
    private String name;
}

package com.huatech.shop.module.dict.param;

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
public class DictParam implements Serializable {
    private Integer pageNumber;
    private Integer pageSize;
    private String name;
}

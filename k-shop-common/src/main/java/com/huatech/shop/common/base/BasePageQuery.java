package com.huatech.shop.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName BasePageQuery
 * @Description TODO
 * @Author like
 * @Date 2019-08-21 13:06
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePageQuery implements Serializable {

    private Integer pageNumber;
    private Integer pageSize;
}

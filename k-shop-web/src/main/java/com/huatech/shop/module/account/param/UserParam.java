package com.huatech.shop.module.account.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName UserParam
 * @Description TODO
 * @Author like
 * @Date 2019-08-16 13:10
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserParam implements Serializable {

    private Integer pageNumber;
    private Integer pageSize;
}

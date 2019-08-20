package com.huatech.shop.module.banner.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName BannerParam
 * @Description TODO
 * @Author like
 * @Date 2019-08-20 23:01
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerParam implements Serializable {

    private String status;
    private String channel;
    private String position;
    private Integer pageNumber;
    private Integer pageSize;
}

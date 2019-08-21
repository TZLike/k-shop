package com.huatech.shop.module.banner.param;

import com.huatech.shop.common.base.BasePageQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
public class BannerParam extends BasePageQuery {

    private String status;
    private String channel;
    private String position;
}

package com.huatech.shop.common.dto;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BannerDto
 * @Description TODO
 * @Author like
 * @Date 2019-08-21 00:22
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerDto implements Serializable {

    private String name; //名称
    private String subTitle; //标题
    private String imgUrl;
    private String position; //显示位置
    private String channel; //渠道
    private String status;
    private Integer sInt;  //
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime; //创建时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;//上线时间


}

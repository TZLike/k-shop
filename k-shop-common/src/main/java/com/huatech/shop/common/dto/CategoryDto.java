package com.huatech.shop.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName CategoryDto
 * @Description TODO
 * @Author like
 * @Date 2019-08-21 13:03
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto implements Serializable {

    private Integer id;
    private Integer categoryNo;
    private String name;
    private String imgUrl;
    private String status;
    @JSONField(format = "yyyy-MM:dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM:dd HH:mm:ss")
    private Date updateTime;

}

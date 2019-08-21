package com.huatech.shop.module.category.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.huatech.shop.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_category")
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer categoryNo;

    private String name;

    private String imgUrl;

    private Boolean status;

    @JSONField(format = "yyyy-MM:dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM:dd HH:mm:ss")
    private Date updateTime;


}
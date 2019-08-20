package com.huatech.shop.module.resource.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.huatech.shop.common.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_resource")
@Data
public class Resource extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "parent_id")
    private Integer parentId;
    private String name;
    private Integer type;
    private Integer level;
    @Column(name = "source_url")
    private String sourceUrl;
    @Column(name = "is_hide")
    private Integer isHide;
    private Integer sort;
    @Column(name = "source_key")
    private String sourceKey;
    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "create_time")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "update_time")
    private Date updateTime;
    private String description;


}
package com.huatech.shop.module.role.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.huatech.shop.common.base.BaseEntity;
import com.huatech.shop.module.resource.entity.Resource;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "t_role")
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;
    private String name;
    @Column(name = "role_key")
    private String roleKey;
    private Integer status;
    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "create_time")
    private Date createTime;
    private String description;
    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "update_time")
    private Date updateTime;
    @Transient
    private Integer roleId;
    private List<Resource> resources;


}
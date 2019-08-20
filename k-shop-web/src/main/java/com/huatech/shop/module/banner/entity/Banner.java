package com.huatech.shop.module.banner.entity;

import com.huatech.shop.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_banner")
public class Banner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String subtitle;

    private String imgUrl;

    private String position;

    private String url;

    private String status;

    private Date createTime;

    private Date startTime;

    private String channel;

    private static final long serialVersionUID = 1L;


}
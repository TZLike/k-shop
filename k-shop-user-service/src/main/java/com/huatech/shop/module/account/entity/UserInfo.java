package com.huatech.shop.module.account.entity;

import com.huatech.shop.common.base.BaseEntity;
import com.huatech.shop.module.auth.entity.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_wx_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String idNum;

    private String wxPhoneNum;

    private String phoneNum;

    private Date birthday;

    private String sex;

    private String profileImgUrl;

    private String openid;

    private String unionId;

    private String wxName;

    private Date createTime;

    private Date bindTime;

    private String remark1;

    private String remark2;

    private String remark3;
    private Token token;


}
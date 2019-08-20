package com.huatech.shop.module.account.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.huatech.shop.common.base.BaseEntity;
import com.huatech.shop.module.role.entity.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "t_user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid; //主键
    @Column(name = "nick_name")
    private String nickName; //昵称
    private String password;//密码
    private Integer sex;//性别
    private String telephone;//手机号
    @Column(name = "user_name")
    private String userName;//用户名
    private String email; //邮箱
    @Column(name = "locked")
    private Integer locked; //是否已经锁定
    private String description; //描述
    /**
     * 出生日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date birthday; //生日

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime; //创建日期

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @JSONField(format = "yyyy-MM-dd")
    private Date updateTime;//更新时间
    private List<Role> roles;//角色列表


}
package com.huatech.shop.module.role.entity;

import com.huatech.shop.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "t_role_resource")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleResourceKey extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    @Id
    private Integer resourceId;

}
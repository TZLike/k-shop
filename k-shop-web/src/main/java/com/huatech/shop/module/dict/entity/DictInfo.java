package com.huatech.shop.module.dict.entity;

import com.huatech.shop.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "t_dict_info")
@AllArgsConstructor
@NoArgsConstructor
public class DictInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "dict_code")
    private String dictCode;
    @Column(name = "type_code")
    private String typeCode;
    private String info;
    private String remark;


}
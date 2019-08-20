package com.huatech.shop.module.dict.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.huatech.shop.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "t_dict_type")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DictType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "type_code")
    private String typeCode;
    @Column(name = "type_name")
    private String typeName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String remark;


}
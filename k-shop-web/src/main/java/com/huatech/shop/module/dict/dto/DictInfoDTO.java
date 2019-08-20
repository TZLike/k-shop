package com.huatech.shop.module.dict.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName DictInfoDTO
 * @Description TODO
 * @Author like
 * @Date 2019-08-19 01:22
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictInfoDTO implements Serializable {
    private Integer id;
    private String typeName; //基础类型名称
    private String dictCode; //业务编码
    private String info; //信息
    private String remark; //备注

}
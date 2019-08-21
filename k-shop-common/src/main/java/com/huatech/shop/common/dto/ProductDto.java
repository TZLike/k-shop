package com.huatech.shop.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author leek
 * @Date 2018-09-01 下午3:04
 * @Version 1.0
 * @Description
 */

@Data
public class ProductDto implements Serializable {

    private String productId;  //商品主键
    private String categoryNo; //所属商品分类
    private String categoryName;//所属分类名称
    private String productImage; //商品标记图
    private String productName; //商品名称
    private Integer productStatus; //商品上架状态  1已上架 2 已下架 3未上架
    private String productStatusText;
    private Integer productStock;  //商品库存
    @JSONField(format = "yyyy-MM-dd HH:mm:ss ")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss ")
    private Date updateTime;
    private String productDescription; //商品描述
    private Date productStartTime;
    private Date productEndTime;
    private Integer productSalesType;
    private String productSalesTypeText;
    private Integer productIfRecommend;  //是否推荐 0：否 1：是
    private String productIfRecommendText;
    private Double productNowPrice;
    private String productNowPriceDescription;
    private Double productOriginalPrice;
    private Integer productSoldNum; //已售数量
    private String productSummary;   //商品简介
    private Integer productIfCanInvoice;  //是否是否可以开发票
    private String productIfCanInvoiceText;
    private Integer productGoodsType;
    private String productGoodsTypeText;
    private String productUpdateUser;
    private String productCreateUser;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss ")
    private Date productCommendStartTime;  //推荐商品结束时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss ")
    private Date productCommendEndTime;


}

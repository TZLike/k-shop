package com.huatech.shop.module.product.entity;

import com.huatech.shop.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    @Id
    @Column(name = "product_id")
    private String productId;  //商品主键
    @Column(name = "category_no")
    private String categoryNo; //所属商品分类
    @Column(name = "product_image")
    private String productImage; //商品标记图
    @Column(name = "product_name")
    private String productName; //商品名称
    @Column(name = "product_status")
    private Integer productStatus; //商品上架状态  1已上架 2 已下架 3未上架
    @Column(name = "product_stock")
    private Integer productStock;  //商品库存
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "product_description")
    private String productDescription; //商品描述
    @Column(name = "product_start_time")  //商品上架时间
    private Date productStartTime;
    @Column(name = "product_end_time")  //商品下架时间
    private Date productEndTime;
    @Column(name = "product_sales_type") //销售平台：1所有平台，2微信,3客户端
    private Integer productSalesType;
    @Column(name = "product_if_recommend")
    private Integer productIfRecommend;  //是否推荐 0：否 1：是
    @Column(name = "product_now_price")
    private Double productNowPrice;
    @Column(name = "product_now_price_description")
    private String productNowPriceDescription;
    @Column(name = "product_original_price")
    private Double productOriginalPrice;
    @Column(name = "product_sold_num")
    private Integer productSoldNum; //已售数量
    @Column(name = "product_summary")
    private String productSummary;   //商品简介
    @Column(name = "product_if_can_invoice")
    private Integer productIfCanInvoice;  //是否是否可以开发票
    @Column(name = "product_goods_type")
    private Integer productGoodsType;
    @Column(name = "product_update_user")  //商品类型 0:虚拟商品 1:实物商品
    private String productUpdateUser;
    @Column(name = "product_create_user")
    private String productCreateUser;
    @Column(name = "product_commend_start_time")  //推荐商品开始时间
    private Date productCommendStartTime;  //推荐商品结束时间
    @Column(name = "product_commend_end_time")
    private Date productCommendEndTime;


}
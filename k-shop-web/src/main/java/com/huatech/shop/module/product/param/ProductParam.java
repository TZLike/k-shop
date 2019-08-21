package com.huatech.shop.module.product.param;

import com.huatech.shop.common.base.BasePageQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author leek
 * @Date 2018-09-01 下午3:04
 * @Version 1.0
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductParam extends BasePageQuery {

    private String productId;//
    @NotBlank(message = "商品分类不能为空")
    private String categoryNo; //所属商品分类
    @NotBlank(message = "商品图片不能为空")
    private String productImage; //商品标记图
    @NotBlank(message = "商品名称不能为空")
    private String productName; //商品名称
    @NotNull(message = "商品上架状态不能为空")
    private Integer productStatus; //商品上架状态  1已上架 2 已下架 3未上架
    @NotNull(message = "商品库存不能为空")
    @Min(value = 1, message = "库存最小值为1")
    private Integer productStock;  //商品库存
    private String productDescription; //商品描述
    @NotNull(message = "商品销售渠道不能为空")
    private Integer productSalesType;
    @NotNull(message = "商品推荐不能为空")
    private Integer productIfRecommend;  //是否推荐 0：否 1：是
    @NotNull(message = "商品现价不能为空")
    private Double productNowPrice;
    private String productNowPriceDescription;
    @NotNull(message = "商品元价不能为空")
    @Min(value = 1, message = "原价最小值为1")
    private Double productOriginalPrice;
    private String productSummary;   //商品简介
    @NotNull(message = "商品类型不能为空")
    private Integer productGoodsType;
    private String productCommendStartTime;  //推荐商品结束时间

    private String productCommendEndTime;

    private String productCreateUser;

    private Integer productIfCanInvoice;//是否能开发票
}

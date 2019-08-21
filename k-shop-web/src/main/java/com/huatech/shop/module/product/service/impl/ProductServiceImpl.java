package com.huatech.shop.module.product.service.impl;

import com.github.pagehelper.PageInfo;
import com.huatech.shop.common.base.IBaseMapper;
import com.huatech.shop.common.base.impl.BaseServiceImpl;
import com.huatech.shop.common.dto.ProductDto;
import com.huatech.shop.module.product.entity.Product;
import com.huatech.shop.module.product.mapper.ProductMapper;
import com.huatech.shop.module.product.param.ProductParam;
import com.huatech.shop.module.product.service.IProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author leek
 * @Date 2019-08-22 下午3:03
 * @Version 1.0
 * @Description
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, String> implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public IBaseMapper<Product, String> getBaseMapper() {
        return this.productMapper;
    }


    @Override
    public void saveOrUpdate(ProductParam param) {

        //检查参数是否合法
//        BeanValidator.check(param);
        //更新
        if (StringUtils.isNotBlank(param.getProductId())) {

            Product product = productMapper.selectByPrimaryKey(param.getProductId());
            if (product == null) {
//                throw new ExceptionCustomer(ProductStatuesEnumType.PRODUCT_NOT_EXISTS.getName(), ProductStatuesEnumType.PRODUCT_NOT_EXISTS.getCode());
            }
            BeanUtils.copyProperties(param, product);
            product.setUpdateTime(new Date());
            product.setProductCreateUser(product.getProductCreateUser());
            productMapper.updateByPrimaryKeySelective(product);
        } else {
            //增加商品不需要判断商品中是否存在同名的商品
            Product product = new Product();
            BeanUtils.copyProperties(param, product);
//            product.setProductId(UUIDBuild.getUUID()); //商品编号
            product.setCreateTime(new Date());
//            if (product.getProductStatus() == ProductStatuesEnumType.PRODUCT_STATUES_UP.getCode()) {
//                product.setProductStartTime(new Date());
//            }
            productMapper.insertSelective(product);


        }

    }

    //根据条件查询商品列表
    @Override
    public PageInfo<ProductDto> findCategoryByStatus(ProductParam param, int pageNumber, int pageSize) {

        return null;

//        ProductExample example = new ProductExample();
//        ProductExample.Criteria criteria = example.createCriteria();
//        if (StringUtils.isNotBlank(param.getProductName())) {
//            //商品名称条件
//            criteria.andProductNameLike(param.getProductName() + "%");
//        }
//        if (param.getProductStatus() != null) {
//            //商品状态
//            criteria.andProductStatusEqualTo(param.getProductStatus());
//
//        }
//        if (param.getProductGoodsType() != null) {
//
//            criteria.andProductGoodsTypeEqualTo(param.getProductGoodsType());
//
//        }
//        if (param.getProductIfRecommend() != null) {
//
//            criteria.andProductIfRecommendEqualTo(param.getProductIfRecommend());
//
//        }
//        if (param.getProductIfCanInvoice() != null) {
//
//            criteria.andProductIfCanInvoiceEqualTo(param.getProductIfCanInvoice());
//
//        }
//        if (param.getProductSalesType() != null) {
//
//            if (param.getProductSalesType().equals(ProductStatuesEnumType.PRODUCT_SALES_TYPE_ALL.getCode() + "")) {
//                criteria.andProductSalesTypeIn(Lists.newArrayList(ProductStatuesEnumType.PRODUCT_SALES_TYPE_MOBILE.getCode(), ProductStatuesEnumType.PRODUCT_SALES_TYPE_WX.getCode()));
//            } else {
//                criteria.andProductSalesTypeEqualTo(param.getProductSalesType());
//            }
//
//        }
//        if (StringUtils.isNotBlank(param.getCategoryNo())) {
//
//            criteria.andCategoryNoEqualTo(param.getCategoryNo());
//
//        }
//        PageHelper.startPage(pageNumber, pageSize);
//
//        List<Product> products = productMapper.selectByExample(example);
//        int count = productMapper.selectAll().size();
//
//        return new PageInfo<>(ProductDto.product2ProductDto(products),count);
    }

    //更新商品的状态
    @Override
    public void updateProductStatus(String productId, Integer status) {
//
//        Product db_product = productMapper.selectByPrimaryKey(productId);
//        if (db_product == null) {
//            throw new ExceptionCustomer(ProductStatuesEnumType.PRODUCT_NOT_EXISTS.getName(), ProductStatuesEnumType.PRODUCT_NOT_EXISTS.getCode());
//        }
//        db_product.setProductStatus(status);
//        db_product.setUpdateTime(new Date());
//        productMapper.updateByPrimaryKey(db_product);


    }

    //根据productNo来查询商品
    @Override
    public List<Product> findProductByCategoryNo(String categoryNo) {

//        ProductExample example = new ProductExample();
//        ProductExample.Criteria criteria = example.createCriteria();
//        criteria.andCategoryNoEqualTo(categoryNo);
//        List<Product> products = productMapper.selectByExample(example);
//        return products;
        return null;

    }
}

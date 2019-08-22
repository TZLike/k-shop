package com.huatech.shop.base.constants;

import com.huatech.shop.common.constants.ApiConstants;

/**
 * @ClassName ShopConstants
 * @Description TODO
 * @Author like
 * @Date 2019-08-16 13:28
 * @Version 1.0
 **/
public class ShopConstants extends ApiConstants {

    /**
     * 用户相关
     */
    public static class User {

        public static final String USER_NAME_EXIST = "用户名已经存在";
        public static final String PASSWORD_ORIGINAL = "12345678";
        public static final String USER_SEX = "10000";
        public static final String USER_STATUS = "30000";

    }

    /**
     * 角色相关
     */
    public static class Role {
        public static final String ROLE_STATUS = "40000";

    }

    /**
     * 资源相关
     */

    public static class Resource {
        public static final String RESOURCE_TYPE = "20000";
        public static final String RESOURCE_STATUS = "50000";

    }

    /**
     * Banner相关
     */
    public static class Banner {
        public static final String BANNER_POSITION = "11000";
        public static final String BANNER_CHANNEL = "12000";
        public static final String BANNER_STATUS = "60000";
    }

    /**
     * 商品类目相关
     */
    public static class Category {
        public static final String CATEGORY_STATUS = "13000";
    }

    /**
     * 商品相关
     */
    public static class Product {

        //商品上架状态
        public static final String PRODUCT_UP_AND_DOWN = "14000";
        //商品销售平台
        public static final String PRODUCT_SALE_PLAT = "15000";
        //商品是否为推荐商品
        public static final String PRODUCT_IS_COMMEND = "16000";
        //商品是否可以开具发票
        public static final String PRODUCT_IS_CAN_TICKET = "17000";
        //商品类型
        public static final String PRODUCT_TYPE = "18000";

    }


}

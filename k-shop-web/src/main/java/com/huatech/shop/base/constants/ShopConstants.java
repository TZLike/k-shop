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


}

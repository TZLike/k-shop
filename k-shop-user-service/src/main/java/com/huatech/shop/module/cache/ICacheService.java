package com.huatech.shop.module.cache;

/**
 * @Author leek
 * @Date 2018-10-23 下午1:50
 * @Version 1.0
 * @Description
 */
public interface ICacheService {
    void set(String key, Object value);

    void set(String key, Object value, long expires);

    <T> T get(String key);

    <T> T get(String key, ICallback callback);

    void remove(String key);

    void removePattern(String pattern);

    boolean exists(String key);

    public static interface ICallback {
        <T> T execute();
    }
}

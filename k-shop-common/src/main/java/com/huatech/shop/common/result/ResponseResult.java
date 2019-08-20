package com.huatech.shop.common.result;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ResponseResult
 * @Description TODO
 * @Author like
 * @Date 2019-07-22 16:04
 * @Version 1.0
 **/
public class ResponseResult {
    private Map<String, Object> meta = new HashMap();
    private Map<String, Object> data = new HashMap();

    public ResponseResult() {
    }

    public Map<String, Object> getMeta() {
        return this.meta;
    }

    public ResponseResult setMeta(Map<String, Object> meta) {
        this.meta = meta;
        return this;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public ResponseResult setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public ResponseResult addMeta(String key, Object object) {
        this.meta.put(key, object);
        return this;
    }

    public ResponseResult addData(String key, Object object) {
        this.data.put(key, object);
        return this;
    }

    public ResponseResult ok(int statusCode, String statusMsg) {
        this.addMeta("success", Boolean.TRUE);
        this.addMeta("code", statusCode);
        this.addMeta("message", statusMsg);
        return this;
    }

    public ResponseResult error(int statusCode, String statusMsg) {
        this.addMeta("success", Boolean.FALSE);
        this.addMeta("code", statusCode);
        this.addMeta("message", statusMsg);
        return this;
    }
}

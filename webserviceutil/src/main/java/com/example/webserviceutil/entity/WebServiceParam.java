package com.example.webserviceutil.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数的封装类
 * @author 简建鸿 2016/6/2
 * @version 1.0
 */
public class WebServiceParam {
    /**
     * 请求的url
     */
    private String requestUrl;
    /**
     * 请求数据的映射类型
     */
    private Class clazz;
    /**
     * 请求的发生, eg:GET或者POST
     */
    private String method;
    /**
     * 请求的参数，存储键值对
     */
    private Map<String, Object> params = new HashMap<>();

    /**
     * WebServiceParam构造方法
     * @param requestUrl 请求的url
     * @param method 请求方式
     * @param clazz 请求数据类型
     */
    public WebServiceParam(String requestUrl, String method, Class clazz) {
        this.requestUrl = requestUrl;
        this.method = method;
        this.clazz = clazz;
    }

    /**
     * 添加请求参数
     * @param key 参数名
     * @param value 参数值
     */
    public void addParam(String key, Object value) {
        params.put(key, value);
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}

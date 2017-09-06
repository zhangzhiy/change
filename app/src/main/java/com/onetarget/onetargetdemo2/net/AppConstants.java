package com.onetarget.onetargetdemo2.net;

public interface AppConstants {

    /*********************
     * use for server
     *******************************/

    String APP_BASE_URL = "http://192.168.10.232:8080/zzy/";

    boolean DEBUG = true;

    String RESPONSE_CACHE = "netCache";

    long RESPONSE_CACHE_SIZE = 10 * 1024 * 1024;

    long HTTP_CONNECT_TIMEOUT = 1000 * 30;

    long HTTP_READ_TIMEOUT = HTTP_CONNECT_TIMEOUT;


    int NET_OK_CODE=200;
    /**
     * 设缓存有效期为两天
     */
    long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    String CACHE_CONTROL_AGE = "max-age=0";
}

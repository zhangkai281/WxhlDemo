package com.wuxianedu.demo;

import android.app.Application;

/**
 * Created by caibing.zhang on 2016/10/20.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /*
        //直接初始化后，一切采用默认设置。
//        NoHttp.initialize(this);

        //自定义配置
        NoHttp.Config config = new NoHttp.Config();
        // 设置全局连接超时时间，单位毫秒
        config.setConnectTimeout(30000);
        // 设置全局服务器响应超时时间，单位毫秒
        config.setReadTimeout(30000);// 保存到数据库

        config.setCacheStore(new DBCacheStore(this).setEnable(true)); // 如果不使用缓存，设置false禁用。
        // 或者保存到SD卡
//        config.setCacheStore(new DiskCacheStore(this));

        // 使用OkHttp
        config.setNetworkExecutor(new OkHttpNetworkExecutor());
        // 或者使用HttpURLConnection
//        config.setNetworkExecutor(new URLConnectionNetworkExecutor());

        NoHttp.initialize(this,config);

        Logger.setDebug(true);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        Logger.setTag("--Main--");// 设置NoHttp打印Log的tag。*/
    }
}

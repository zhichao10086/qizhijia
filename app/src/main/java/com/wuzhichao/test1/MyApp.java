package com.wuzhichao.test1;

import android.app.Application;

import com.wuzhichao.test1.dragger.components.AppComponent;
import com.wuzhichao.test1.dragger.components.DaggerAppComponent;
import com.wuzhichao.test1.dragger.modules.AppModule;
import com.wuzhichao.test1.dragger.modules.NetModule;

import org.xutils.x;

import static com.wuzhichao.test1.config.Config.BASE_URL;

/**
 * Created by 黑客 on 2017/7/1.
 *
 */

public class MyApp extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //ImageLoaderUtil.init(this);
        x.Ext.init(this);
        x.Ext.setDebug(true);

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(this,BASE_URL))
                .build();
        System.out.println("MyApp开始创建？");

    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

}

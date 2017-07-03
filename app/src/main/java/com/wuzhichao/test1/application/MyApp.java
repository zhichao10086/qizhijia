package com.wuzhichao.test1.application;

import android.app.Application;

import com.wuzhichao.test1.dragger.components.NetComponent;
import com.wuzhichao.test1.dragger.modules.AppModule;
import com.wuzhichao.test1.dragger.modules.NetModule;

import static com.wuzhichao.test1.config.Config.BASE_URL;

/**
 * Created by 黑客 on 2017/7/1.
 */

public class MyApp extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        mNetComponent = DraggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule(BASE_URL))
                .build();

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mAppComponent = com.codepath.dagger.components.DaggerNetComponent.create();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}

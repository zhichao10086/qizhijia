package com.wuzhichao.test1.dragger.components;

import com.wuzhichao.test1.dragger.modules.AppModule;
import com.wuzhichao.test1.dragger.modules.NetModule;
import com.wuzhichao.test1.mvp.view.activity.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 黑客 on 2017/7/1.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(HomeActivity activity);
}

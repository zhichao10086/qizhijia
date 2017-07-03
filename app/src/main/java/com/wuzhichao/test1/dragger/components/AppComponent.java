package com.wuzhichao.test1.dragger.components;

import com.wuzhichao.test1.mvp.view.activity.HomeActivity;

import javax.inject.Singleton;

/**
 * Created by 黑客 on 2017/7/1.
 */

@Singleton
public interface AppComponent {
    void inject(HomeActivity activity);
}

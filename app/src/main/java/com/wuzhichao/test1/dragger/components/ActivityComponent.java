package com.wuzhichao.test1.dragger.components;

import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wuzhichao.test1.dragger.MyAnnonation.ActivityScoped;
import com.wuzhichao.test1.dragger.modules.ActivityModule;
import com.wuzhichao.test1.dragger.qualifier.ActivityContext;
import com.wuzhichao.test1.dragger.qualifier.AppContext;
import com.wuzhichao.test1.mvp.data.Source.DataRepository;
import com.wuzhichao.test1.mvp.view.baseUI.BaseActivity;

import org.xutils.ViewInjector;

import dagger.Component;

/**
 * Created by 黑客 on 2017/7/6.
 */

@ActivityScoped
@Component(dependencies = {AppComponent.class},
        modules = {ActivityModule.class})
public interface ActivityComponent {

    @ActivityContext
    Context getActivityContext();

    DataRepository getDataRepository();

    @AppContext
    Context getContext();

    ImageLoader getImageLoader();

    ViewInjector getViewInjector();

    void inject(BaseActivity activity);

}

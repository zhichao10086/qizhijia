package com.wuzhichao.test1.dragger.components;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wuzhichao.test1.dragger.MyAnnonation.BaseFragmentScoped;
import com.wuzhichao.test1.dragger.modules.FragmentModule;
import com.wuzhichao.test1.dragger.qualifier.ActivityContext;
import com.wuzhichao.test1.dragger.qualifier.AppContext;
import com.wuzhichao.test1.dragger.qualifier.ChildFragmentManager;
import com.wuzhichao.test1.mvp.data.Source.DataRepository;
import com.wuzhichao.test1.mvp.view.baseUI.BaseFragment;

import org.xutils.ViewInjector;

import dagger.Component;

/**
 * Created by 黑客 on 2017/8/2.
 */

@BaseFragmentScoped
@Component(dependencies = {ActivityComponent.class},
        modules = {FragmentModule.class})
public interface FragmentComponent {

    @ActivityContext
    Context getActivityContext();

    DataRepository getDataRepository();

    @AppContext
    Context getContext();

    ImageLoader getImageLoader();

    ViewInjector getViewInjector();

    @ChildFragmentManager
    FragmentManager getFragmentManager();

    void inject(BaseFragment fragment);
}

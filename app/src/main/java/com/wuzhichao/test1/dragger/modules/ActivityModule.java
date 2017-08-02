package com.wuzhichao.test1.dragger.modules;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.wuzhichao.test1.dragger.MyAnnonation.ActivityScoped;
import com.wuzhichao.test1.dragger.qualifier.ActivityContext;
import com.wuzhichao.test1.mvp.view.baseUI.BaseActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 黑客 on 2017/7/6.
 */

@Module
public class ActivityModule {

    private BaseActivity mActivity;


    public ActivityModule(BaseActivity activity){
        mActivity = activity;
    }

    @Provides
    @ActivityScoped
    @ActivityContext
    public Context provideActivityContext(){
        return mActivity;
    }

    @Provides
    @ActivityScoped
    @Named("ActivityFragmentManager")
    public FragmentManager provideFragmentManager(){return mActivity.getSupportFragmentManager();}




}

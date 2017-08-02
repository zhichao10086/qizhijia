package com.wuzhichao.test1.dragger.modules;

import com.wuzhichao.test1.dragger.MyAnnonation.FragmentScoped;
import com.wuzhichao.test1.mvp.contract.pingguContract.CustomCameraFragmentContract;
import com.wuzhichao.test1.mvp.view.fragment.pingguFragment.CustomCameraFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 黑客 on 2017/7/31.
 */

@Module
public class CustomCameraFragmentModule {

    private CustomCameraFragment mFragment;

    public CustomCameraFragmentModule(CustomCameraFragment mCustomCameraFragment) {
        this.mFragment = mCustomCameraFragment;
    }

    @Provides
    @FragmentScoped
    public CustomCameraFragmentContract.View provideCustomCameraFragment(){
        return mFragment;
    }

}

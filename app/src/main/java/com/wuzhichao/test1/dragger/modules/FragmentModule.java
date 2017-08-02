package com.wuzhichao.test1.dragger.modules;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.wuzhichao.test1.dragger.qualifier.ChildFragmentManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 黑客 on 2017/8/2.
 */

@Module
public class FragmentModule {

    private final Fragment mFragment;

    public FragmentModule(Fragment fragment){
        this.mFragment = fragment;
    }

    @Provides
    @ChildFragmentManager
    public FragmentManager provideChildFragmentManager() { return mFragment.getChildFragmentManager(); }
}

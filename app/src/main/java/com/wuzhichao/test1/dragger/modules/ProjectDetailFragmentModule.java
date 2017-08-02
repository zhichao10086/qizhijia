package com.wuzhichao.test1.dragger.modules;

import android.support.v4.app.Fragment;

import com.wuzhichao.test1.mvp.contract.homeContract.ProjectDetailFragmentContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 黑客 on 2017/7/28.
 */

@Module
public class ProjectDetailFragmentModule {

    private Fragment mFragment;

    public ProjectDetailFragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    public ProjectDetailFragmentContract.View provideProjectDetialContractView(){
        return (ProjectDetailFragmentContract.View) mFragment;
    }
}

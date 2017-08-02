package com.wuzhichao.test1.dragger.modules;

import android.support.v4.app.Fragment;

import com.wuzhichao.test1.mvp.contract.homeContract.PingguFragmentContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 黑客 on 2017/7/13.
 */

@Module
public class PingguFragmentModule{

    private Fragment mFragment;

    public PingguFragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    public PingguFragmentContract.View providePingguContractView(){
        return (PingguFragmentContract.View) mFragment;
    }
}

package com.wuzhichao.test1.dragger.modules;

import android.support.v4.app.Fragment;

import com.wuzhichao.test1.mvp.contract.homeContract.MineFragmentContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 黑客 on 2017/7/13.
 */

@Module
public class MineFragmentModule{

    private Fragment mFragment;

    public MineFragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    public MineFragmentContract.View provideMineContractView(){
        return (MineFragmentContract.View) mFragment;
    }
}

package com.wuzhichao.test1.dragger.modules;

import android.support.v4.app.Fragment;

import com.wuzhichao.test1.dragger.MyAnnonation.FragmentScoped;
import com.wuzhichao.test1.mvp.contract.LoginOrRegisterContract.LoginFragmentContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 黑客 on 2017/7/19.
 */

@Module
public class LoginFragmentModule {

    private final Fragment mFragment;

    public LoginFragmentModule(Fragment mFragment){

        this.mFragment = mFragment;
    }

    @Provides
    @FragmentScoped
    public LoginFragmentContract.View provideLoginFragment(){
        return (LoginFragmentContract.View)mFragment;
    }

}

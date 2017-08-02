package com.wuzhichao.test1.dragger.components;

import com.wuzhichao.test1.dragger.MyAnnonation.FragmentScoped;
import com.wuzhichao.test1.dragger.modules.LoginFragmentModule;
import com.wuzhichao.test1.mvp.view.fragment.LoginFragment;

import dagger.Component;

/**
 * Created by 黑客 on 2017/7/19.
 */

@FragmentScoped
@Component(dependencies = FragmentComponent.class,modules = {LoginFragmentModule.class})
public interface LoginFragmentComponent {
    void inject(LoginFragment fragment);
}
